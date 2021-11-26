package com.example.wearables_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

// This is the GPS component of our app! It will transition to the game after reaching
// a new location and adds a new feature to the game in the process!

public class GPSActivity extends AppCompatActivity implements LocationListener  {

    final int PERMISSION_REQUEST_CODE = 0;

    Location lunarLander;
    Location spaceExplorer;
    Location constellationDiscoverer;
    Location eventHorizon;
    Location dodgeAndWeave;
    Location finalFrontier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsactivity);

        lunarLander = new Location(LocationManager.GPS_PROVIDER);
        //lunarLander.setLongitude(); need to be set in the future
        //lunarLander.setLatitude(); wont include in the rest of the location initializations

        //will need to set the longitude and latitude on these once we have them
        spaceExplorer = new Location(LocationManager.GPS_PROVIDER);
        constellationDiscoverer = new Location(LocationManager.GPS_PROVIDER);
        eventHorizon = new Location(LocationManager.GPS_PROVIDER);
        dodgeAndWeave = new Location(LocationManager.GPS_PROVIDER);
        finalFrontier = new Location(LocationManager.GPS_PROVIDER);

        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (permissionGranted)
        {
            //start location services
            Log.d("GPS_Logs", "User already granted permission before, Start GPS NOW");
            startGPS();
        }
        else
        {
            //request permission for location services
            Log.d("GPS_Logs", "User has not granted persmission, REQUESTING");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }

    }

    public void startGPS()
    {
        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (permissionGranted)
        {
            Log.d("GPS_Logs", "Permission Granted in startGPS()");
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
        }
    }
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if(requestCode == PERMISSION_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
            {
                //user denied access
                //terminate activity
                Log.d("GPS_Logs" ,"User denied permission right now, Exit");
                finish();
            }
            else
            {
                //user allowed access
                //same as start location services
                Log.d("GPS_Logs","User granted permission right now, Start GPS now");
                startGPS();
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location)
    {
        Log.d("GPS_Logs", "New location recieved. Long: "+location.getLongitude()+" Lat: "+location.getLatitude());

        if (location.distanceTo(lunarLander) < 50)
        {
            Log.d("GPS_Logs", "You are at lunarLander site");
            //starting point? irrelevant if there is no order to the gps locations
            //functionality for gps location here

        }
        else if (location.distanceTo(spaceExplorer) < 50)
        {
            Log.d("GPS_Logs", "You are at spaceExplorer site");
            //functionality for gps location here

        }
        else if (location.distanceTo(constellationDiscoverer) < 50)
        {
            Log.d("GPS_Logs", "You are at constellationDiscoverer site");
            //functionality for gps location here
        }
        else if (location.distanceTo(eventHorizon) < 50)
        {
            Log.d("GPS_Logs", "You are at eventHorizon site");
            //functionality for gps location here
        }
        else if (location.distanceTo(dodgeAndWeave) < 50)
        {
            Log.d("GPS_Logs", "You are at dodgeAndWeave site");
            //functionality for gps location here

        }
        else if (location.distanceTo(finalFrontier) < 50)
        {
            Log.d("GPS_Logs", "You are at finalFrontier site");
            //functionality for gps location here

        }


    }
}