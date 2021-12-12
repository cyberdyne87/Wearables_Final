package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.wearables_final.Trophy;

import android.Manifest;
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

        //global int keeps track of where user is
        Global.currentLocation = 0;

        //Instantiate Trophy class
        //Trophy myTroph = new Trophy();

        //cant request permissions within a service
        //we do this here instead, once location services are granted we can start the service -- Dylan
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);

        //starting location service -- Dylan
        Intent intent = new Intent(this, GPSLocation.class);
        startService(intent);

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
                if (Global.currentLocation == 0)
                {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                }
                else if (Global.currentLocation == 1)
                {
                    Intent intent = new Intent(MainActivity.this, LunarLander.class);
                    startActivity(intent);
                    //myTroph.setImg(1);
                }
                else if (Global.currentLocation == 2)
                {
                    Intent intent = new Intent(MainActivity.this, DodgeAndWeave.class);
                    startActivity(intent);
                    //myTroph.setImg(2);
                }
                else if (Global.currentLocation == 3)
                {
                    Intent intent = new Intent(MainActivity.this, ConstellationDiscoverer.class);
                    startActivity(intent);
                    //myTroph.setImg(3);
                }
                else if (Global.currentLocation == 4)
                {
                    Intent intent = new Intent(MainActivity.this, SpaceExplorer.class);
                    startActivity(intent);
                    //myTroph.setImg(4);
                }
                else if (Global.currentLocation == 5)
                {
                    Intent intent = new Intent(MainActivity.this, EventHorizon.class);
                    startActivity(intent);
                    //myTroph.setImg(5);
                }
                else if (Global.currentLocation == 6)
                {
                    Intent intent = new Intent(MainActivity.this, FinalFrontier.class);
                    startActivity(intent);
                    //myTroph.setImg(6);
                }

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