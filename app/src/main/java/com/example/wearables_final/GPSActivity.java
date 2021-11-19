package com.example.wearables_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// This is the GPS component of our app! It will transition to the game after reaching
// a new location and adds a new feature to the game in the process!

public class GPSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsactivity);
    }
}