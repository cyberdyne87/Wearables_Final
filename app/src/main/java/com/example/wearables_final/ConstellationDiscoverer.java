package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ConstellationDiscoverer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation_discoverer);

        Bundle extras= getIntent().getExtras();
        if(extras!=null){


            ImageView frame=findViewById(R.id.constellationDiscoverer);
            frame.setImageResource(extras.getInt("pictureID"));
        }

        Button trophies = (Button) findViewById(R.id.trophyButton3);
        Button game = (Button) findViewById(R.id.playButton3);

        trophies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConstellationDiscoverer.this, Trophy.class);
                startActivity(intent);
            }
        });


        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConstellationDiscoverer.this, GameActivity.class);
                startActivity(intent);
            }
        });


    }
}