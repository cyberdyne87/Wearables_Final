package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class SpaceExplorer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_explorer);

        Bundle extras= getIntent().getExtras();
        if(extras!=null){


            ImageView frame=findViewById(R.id.spaceexplorergps);
            frame.setImageResource(extras.getInt("pictureID"));
        }
    }
}