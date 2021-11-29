package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FinalFrontier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_frontier);

        Bundle extras= getIntent().getExtras();
        if(extras!=null){


            ImageView frame=findViewById(R.id.finalFrontier);
            frame.setImageResource(extras.getInt("pictureID"));
        }

        Button trophies = (Button) findViewById(R.id.trophyButton6);
        Button game = (Button) findViewById(R.id.playButton6);

        trophies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalFrontier.this, FinalFrontierTrophyRoom.class);
                startActivity(intent);
            }
        });


        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalFrontier.this, GameActivity.class);
                startActivity(intent);
            }
        });


    }
}