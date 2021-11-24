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

    // game variables
    Rocketship rocket;
    Random randy;
    boolean canPlay = true;
    int lives = 0; // how many hits can the player take?
    float score = 0f; // how far has the player gotten in km?
    float fuelRemaining = 100f; // how much fuel does the player have left?

    // game objects
    // ArrayList<> objects = new ArrayList<>(); // falling objects currently in play

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

        // set up rocket
        // rocket = new Rocketship(SPRITE_HERE, 100, 100, 0, 0, 4);
    }

    public void draw() {
        // double check to prevent crashes
        if (holder == null) return;

        // start drawing
        Canvas c = holder.lockCanvas();

        // find time elapsed since last draw call
        float timeElapsed = 0f;
        if (previousTime != -1 && canPlay) timeElapsed = SystemClock.elapsedRealtime() / 1000f - previousTime / 1000f;
        previousTime = SystemClock.elapsedRealtime();

        // stop drawing
        holder.unlockCanvasAndPost(c);
    }

    // this checks if the given object is colliding with the rocket
    public boolean rocketTouchCheck(FallingObject obj) {
        if (rocket == null) return false;

        float xDist = Math.abs(rocket.xPos - obj.xPos);
        float yDist = Math.abs(rocket.yPos - obj.yPos);
        if (xDist < rocket.width && yDist < rocket.height) {
            return true;
        }
        else {
            return false;
        }
    }

    // plays a sound with a fresh media player that closes itself when complete
    public void playSound(int sound) {
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
        accel_y = sensorEvent.values[1];
        if (!canPlay)
        {
            accel_x = 0f;
            accel_y = 0f;
        }

        // Log.d("Testing", "X VAL: " + accel_x);
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