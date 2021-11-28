package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class FinalFrontier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_frontier);

        Bundle extras= getIntent().getExtras();
        if(extras!=null){


            ImageView frame=findViewById(R.id.finalfronteirgps);
            frame.setImageResource(extras.getInt("pictureID"));
        }
    }
}