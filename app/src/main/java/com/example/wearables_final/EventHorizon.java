package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EventHorizon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_horizon);

        Bundle extras= getIntent().getExtras();
        if(extras!=null){


            ImageView frame=findViewById(R.id.eventHorizon);
            frame.setImageResource(extras.getInt("pictureID"));
        }

        Button trophies = (Button) findViewById(R.id.trophyButton6);
        Button game = (Button) findViewById(R.id.playButton5);

        trophies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventHorizon.this, Trophy.class);
                startActivity(intent);
            }
        });


        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventHorizon.this, GameActivity.class);
                startActivity(intent);
            }
        });


    }
}