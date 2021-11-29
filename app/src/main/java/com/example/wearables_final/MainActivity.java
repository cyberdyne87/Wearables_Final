package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// This is the starting activity, the main menu!

public class MainActivity extends AppCompatActivity {

    //Ivy's code
    MediaPlayer player;
    boolean paused;
    GPSActivity gpsListener;

    public static void setOnClickListener(View.OnClickListener onClickListener) {
    }

    public void onMusicPressed (View view){
        if (paused == true) {
            player.start();
            paused = false;
        } else {
            player.pause();
            paused = true;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






    //Ivy's code
        Button lunarlander = (Button) findViewById(R.id.startgameButton);
        Button manual = (Button) findViewById(R.id.manualButton);
        Button trophies = (Button) findViewById(R.id.trophyButton);
        Button credits = (Button) findViewById(R.id.creditsButton);
        player = MediaPlayer.create(this, R.raw.startscreenmusic);
        player.setLooping(true);
        player.start();


        lunarlander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LunarLander.class);
                startActivity(intent);
            }
        });


        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Manual.class);
                startActivity(intent);
            }
        });


        trophies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Trophy.class);
                startActivity(intent);
            }
        });


        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Credits.class);
                startActivity(intent);
            }
        });


    }


}