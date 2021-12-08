package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LunarLander extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunar_lander);

        Bundle extras= getIntent().getExtras();
        if(extras!=null){


            ImageView frame=findViewById(R.id.lunarLander);
            frame.setImageResource(extras.getInt("pictureID"));
        }


        Button trophies = (Button) findViewById(R.id.trophyButton1);
        Button game = (Button) findViewById(R.id.playButton1);

        trophies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LunarLander.this, LunarLanderTrophyRoom.class);
                startActivity(intent);
            }
        });


        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LunarLander.this, GameActivity.class);
                startActivity(intent);
            }
        });



    }
}