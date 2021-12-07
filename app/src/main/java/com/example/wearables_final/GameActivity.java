package com.example.wearables_final;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

// this activity contains the SurfaceViewGame!

public class GameActivity extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback {

    // core variables
    SensorManager manager;
    Sensor accel;
    float accel_x = 0f;
    float accel_y = 0f;
    SurfaceHolder holder;
    Animator anim;

    // time tracking
    long previousTime = -1;
    float timeUntilNextObject = 0f;

    // game variables
    Rocketship rocket;
    Random randy;
    DrawnObject fuelStick;
    boolean canPlay = true;
    int lives = 3; // how many hits can the player take?
    float score = 0f; // how far has the player gotten in km?
    float fuelRemaining = 100f; // how much fuel does the player have left?
    float maxFuel = 100f;
    float fuelDrainRate = 5f; // how much fuel is consumed per second?
    float boost = 100f; // when this is equal to 100, the boost is ready
    float boostFillSpeed; // how quickly the boost meter should be filled
    public boolean triggeredBomb = false;

    // generation odds
    float[] objectTypeOdds = new float[]{0.85f, 0.15f}; // 0 = hazard, 1 = "power" up
    float[] hazardOdds = new float[]{0.8f, 0.2f}; // 0 = asteroid, 1 = meteor (if enabled)
    float[] powerOdds = new float[]{0.1f, 0.1f, 0.8f}; // 0 = fuel, 1 = heart, 2 = bomb (if enabled)

    // game objects
    ArrayList<FallingObject> objects = new ArrayList<>(); // falling objects currently in play

    // sprite variables
    Bitmap sprite_asteroid;
    Bitmap sprite_meteor;
    Bitmap sprite_bomb;
    Bitmap sprite_heart;
    Bitmap sprite_fuel;
    Bitmap sprite_rocket;
    Bitmap sprite_starBackground;
    Bitmap sprite_fuelgauge;
    Bitmap sprite_fuelstick;

    // paints
    Paint paint_fuel;
    Paint paint_gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // set up accelerometer
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accel = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accel != null) manager.registerListener(this, accel, SensorManager.SENSOR_DELAY_GAME, SensorManager.SENSOR_DELAY_UI);

        // set up surface view and animate it
        SurfaceView surface = findViewById(R.id.gameView);
        surface.getHolder().addCallback(this);
        anim = new Animator(this);
        anim.start();

        // set up randomness
        randy = new Random();

        // bitmaps
        sprite_asteroid = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid), 256, 256, false);
        sprite_meteor = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.meteor), 196, 196, false);
        sprite_bomb = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bomb), 128, 128, false);
        sprite_heart = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.heart), 128, 128, false);
        sprite_fuel = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fuel), 128, 128, false);
        sprite_rocket = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rocket), 256, 256, false);
        sprite_starBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.starsbackground_vtwo), 1080, 1920, false);
        sprite_fuelgauge = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fuelgage), 256, 256, false);
        sprite_fuelstick = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fuelgagestick_adjusted), 480, 480, false);

        // set up paints
        paint_fuel = new Paint();
        paint_fuel.setTextAlign(Paint.Align.RIGHT);
        paint_fuel.setColor(Color.WHITE);
        paint_fuel.setTextSize(80f);

        paint_gameOver = new Paint();
        paint_gameOver.setTextAlign(Paint.Align.CENTER);
        paint_gameOver.setColor(Color.WHITE);
        paint_gameOver.setTextSize(175f);

        // set up rocket
        rocket = new Rocketship(sprite_rocket, 100, 100, 0, 400, 4);

        fuelStick = new DrawnObject(sprite_fuelstick,0, 0, 0);
    }

    // generates a random obj that falls down the screen
    public FallingObject generateRandomObject(float minX, float maxX) {
        // first, chose a random object type based on odds array
        FallingObject obj = null;
        float num = randy.nextFloat();
        int toSpawn = 0;
        float totalOdds = 0f;

        // pick hazard or powerup
        for (float i : objectTypeOdds) {
            totalOdds += i;
            // if the total is greater than the generated number, we have our answer!
            if (totalOdds > num)
                break;

            toSpawn++;
        }

        // randomize x pos
        float x = randy.nextFloat() * (maxX - minX) + minX;

        // spawn a hazard
        if (toSpawn == 0)
        {
            // first, chose a random object type based on odds array
            num = randy.nextFloat();
            toSpawn = 0;
            totalOdds = 0f;

            // pick a hazard
            for (float i : hazardOdds) {
                totalOdds += i;
                // if the total is greater than the generated number, we have our answer!
                if (totalOdds > num)
                    break;

                toSpawn++;
            }

            if (toSpawn == 1 && !Global.hasMeteors) toSpawn = 0;

            // spawn desired hazard
            switch (toSpawn)
            {
                // spawn asteroid
                case 0:
                    obj = new FallingAsteroid(sprite_asteroid, -1, 5, randy.nextFloat() * 5 + 15, x);
                    break;
                // spawn meteor
                case 1:
                    obj = new FallingAsteroid(sprite_meteor, -1, 5, randy.nextFloat() * 5 + 30, x);
                    break;
            }
        }
        // spawn a powerup
        else if (toSpawn == 1)
        {
            // first, chose a random object type based on odds array
            num = randy.nextFloat();
            toSpawn = 0;
            totalOdds = 0f;

            // pick a powerup
            for (float i : powerOdds) {
                totalOdds += i;
                // if the total is greater than the generated number, we have our answer!
                if (totalOdds > num)
                    break;

                toSpawn++;
            }

            if (toSpawn == 2 && !Global.hasBomb) toSpawn = 1;

            // spawn desired powerup
            switch (toSpawn)
            {
                // spawn asteroid
                case 0:
                    obj = new FallingFuelBonus(sprite_fuel, -1, 5, randy.nextFloat() * 5 + 20, x, 25f);
                    break;
                // spawn meteor
                case 1:
                    obj = new FallingHealthBonus(sprite_heart, -1, 5, randy.nextFloat() * 5 + 20, x);
                    break;
                // spawn bomb
                case 2:
                    obj = new FallingBomb(sprite_bomb, -1, 5, randy.nextFloat() * 5 + 20, x);
            }
        }
        
        return obj;
    }

    public void smartBomb()
    {
        // clear out hazards
        objects.removeIf(obj -> obj instanceof FallingAsteroid || obj instanceof FallingMeteor);

        // reset bomb
        triggeredBomb = false;
    }

    public void draw() {
        // double check to prevent crashes
        if (holder == null) return;

        // start drawing
        Canvas c = holder.lockCanvas();

        c.drawColor(Color.BLACK);

        // find time elapsed since last draw call
        float timeElapsed = 0f;
        if (previousTime != -1 && canPlay) timeElapsed = SystemClock.elapsedRealtime() / 1000f - previousTime / 1000f;
        previousTime = SystemClock.elapsedRealtime();

        // drain fuel
        if (fuelRemaining > 0f) fuelRemaining -= timeElapsed * fuelDrainRate;
        if (fuelRemaining < 0f) {
            fuelRemaining = 0f;
            canPlay = false;
        }

        // gain boost
        if (boost <= 0f) boost += timeElapsed * boostFillSpeed;
        if (boost > 100f) {
            boost = 100f;
        }

        // tick down next object spawn time
        timeUntilNextObject -= timeElapsed;
        if (timeUntilNextObject < 0)
        {
            // spawn a new object if it's time!
            objects.add(generateRandomObject(-400, 400));

            // reset timer by random amount
            timeUntilNextObject = randy.nextFloat() * 0.5f + 1f;
        }

        c.drawBitmap(sprite_starBackground, 0, 0,null);


        // update and draw falling candies
        ListIterator<FallingObject> iterator = objects.listIterator();
        while (iterator.hasNext()){
            // get this obj
            FallingObject obj = iterator.next();

            // update this obj's position (if in play)
            if (canPlay) obj.update();

            // collision checking with bowl
            if (rocketTouchCheck(obj)) {
                obj.consequences(this);
                playSound(obj.collisionSound);
                iterator.remove();
            }

            // check if this obj has passed the bottom of the screen
            if (obj.yPos > c.getHeight()) iterator.remove();

            obj.draw(c);
        }

        // blow up smart bomb
        if (triggeredBomb)
            smartBomb();

        for (FallingObject drawn : objects)
            drawn.draw(c);

        // update rocket
        rocket.update(accel_x, accel_y, -600, 600, -900, 900, 160);
        rocket.draw(c);

        // draw ui
        for (int i = 0; i < lives; i++) {
            c.drawBitmap(sprite_heart, 20 + i * 120, 20, null);
        }

        // lives run out
        if (lives <= 0) canPlay = false;

        // draw temporary fuel meter
        // c.drawBitmap(sprite_fuel, c.getWidth() - 250, 20, null);
        // c.drawText(String.valueOf((int)fuelRemaining), c.getWidth() - 20, 120, paint_fuel);

        // draw better fuel meter
        c.drawBitmap(sprite_fuelgauge, c.getWidth() - 256, 0, null);
        fuelStick.xPos = c.getWidth() / 2f - 16;
        fuelStick.yPos = -(c.getHeight() / 2f) + 16;
        fuelStick.angle = (-(1 - (fuelRemaining / maxFuel)) * 82) - 3;
        fuelStick.draw(c);

        // game over text
        if (!canPlay) c.drawText("GAME OVER", c.getWidth() / 2f, c.getHeight() / 2f, paint_gameOver);

        // stop drawing
        holder.unlockCanvasAndPost(c);
    }

    // this checks if the given object is colliding with the rocket
    public boolean rocketTouchCheck(FallingObject obj) {
        if (rocket == null) return false;

        float xDist = Math.abs(rocket.xPos - obj.xPos);
        float yDist = Math.abs(rocket.yPos - obj.yPos);
        if (xDist < rocket.width + obj.sprite.getWidth() / 3f && yDist < rocket.height + obj.sprite.getHeight() / 3f) {
            return true;
        }
        else {
            return false;
        }
    }

    // plays a sound with a fresh media player that closes itself when complete
    public void playSound(int sound) {
        if (sound == -1) return;
        MediaPlayer player;
        player = MediaPlayer.create(getBaseContext(), sound);
        player.start();

        // close when done
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.release();
            }
        });
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        holder = surfaceHolder;
        draw();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void onQuitButtonPress(View view) {
        finish();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        accel_x = sensorEvent.values[0];
        accel_y = sensorEvent.values[2];
        if (!canPlay)
        {
            accel_x = 0f;
            accel_y = 0f;
        }

        // Log.d("Testing", "X VAL: " + accel_x);
        // Log.d("Testing", "Y VAL: " + accel_y);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onDestroy() {
        anim.finish();
        manager.unregisterListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        super.onDestroy();
    }

    @Override
    public void onStop() {
        anim.finish();
        manager.unregisterListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        super.onStop();
    }

    @Override
    public void onPause() {
        anim.finish();
        manager.unregisterListener(this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        super.onPause();
    }
}