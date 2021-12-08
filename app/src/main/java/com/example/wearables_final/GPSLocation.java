package com.example.wearables_final;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

//has the same functionality as GPSActivity.java but modified to work as a service instead
//allows locations services to be used across the entire app -- Dylan
public class GPSLocation extends Service
{
    public LocationManager locationManager;
    public MyLocationListener myListener;
    public boolean permissionGranted;

    final int PERMISSION_REQUEST_CODE = 0;

    Location lunarLander;
    Location spaceExplorer;
    Location constellationDiscoverer;
    Location eventHorizon;
    Location dodgeAndWeave;
    Location finalFrontier;

    public GPSLocation() {
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        Log.d("GPS_Logs", "Location services started");

        lunarLander = new Location(LocationManager.GPS_PROVIDER);
        lunarLander.setLongitude(-104.008264);
        lunarLander.setLatitude(43.9463683); //Moon, South Dakota

        spaceExplorer = new Location(LocationManager.GPS_PROVIDER);
        spaceExplorer.setLongitude(-80.694108);
        spaceExplorer.setLatitude(28.6144578); //Nasa

        constellationDiscoverer = new Location(LocationManager.GPS_PROVIDER);
        constellationDiscoverer.setLongitude(23.4716567);
        constellationDiscoverer.setLatitude(37.3287894); //Hydra Greece

        eventHorizon = new Location(LocationManager.GPS_PROVIDER);
        eventHorizon.setLongitude(-155.576466);
        eventHorizon.setLatitude(19.5363584 ); //Mauna Loa Observatory

        dodgeAndWeave = new Location(LocationManager.GPS_PROVIDER);
        dodgeAndWeave.setLongitude(-69.2305127);
        dodgeAndWeave.setLatitude(45.1841174); //weaving mill

        finalFrontier = new Location(LocationManager.GPS_PROVIDER);
        finalFrontier.setLongitude(-141.7476875);
        finalFrontier.setLatitude(60.1648125); //Alaska

        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myListener = new MyLocationListener();

        if (permissionGranted)
        {
            //start location services
            Log.d("GPS_Logs", "User already granted permission before, Start GPS NOW");
            myListener.startGPS(this);
        }
        else
        {
            //request permission for location services
            Log.d("GPS_Logs", "User has not granted persmission, REQUESTING");
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);


        }
    }

    //@Override
    /*
    public void onStartCommand(Intent intent, int startId)
    {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myListener = new MyLocationListener();
        permissionGranted =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;
        if (permissionGranted)
        {
            Log.d("GPS_Logs", "Permission Granted");
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, (LocationListener) myListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0, myListener);

    } */



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //wont need this ^^
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("GPS_Logs", "Service stopped, onDestroy()");
        locationManager.removeUpdates(myListener);
    }

    public class MyLocationListener implements LocationListener
    {
        public void startGPS(Context context)
        {
            //boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            boolean permissionGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            if (permissionGranted)
            {
                Log.d("GPS_Logs", "Permission Granted in startGPS()");

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, (LocationListener) this);
            }
        }
        /*
        public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults)
        {
            if(requestCode == PERMISSION_REQUEST_CODE)
            {
                if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                {
                    //user denied access
                    //terminate activity
                    Log.d("GPS_Logs" ,"User denied permission right now, Exit");
                    return;
                }
                else
                {
                    //user allowed access
                    //same as start location services
                    Log.d("GPS_Logs","User granted permission right now, Start GPS now");
                    //myListener.startGPS(this);
                }
            }
        }

         */

        @Override
        public void onLocationChanged(@NonNull Location location)
        {

            Log.d("GPS_Logs", "New location recieved. Long: "+location.getLongitude()+" Lat: "+location.getLatitude());

            if (location.distanceTo(lunarLander) < 50)
            {
                Log.d("GPS_Logs", "You are at lunarLander site");

                //starting point? irrelevant if there is no order to the gps locations
                //functionality for gps location here
                Global.currentLocation = 1;
                //no game changes on first location

            }
            else if (location.distanceTo(spaceExplorer) < 50)
            {
                Log.d("GPS_Logs", "You are at spaceExplorer site");

                //functionality for gps location here
                Global.currentLocation = 4;
                Global.hasMeteors = true;

            }
            else if (location.distanceTo(constellationDiscoverer) < 50)
            {
                Log.d("GPS_Logs", "You are at constellationDiscoverer site");
                //functionality for gps location here
                Global.currentLocation = 3;
                //inc global speed
            }
            else if (location.distanceTo(eventHorizon) < 50)
            {
                Log.d("GPS_Logs", "You are at eventHorizon site");
                //functionality for gps location here
                Global.currentLocation = 5;
                Global.hasBomb = true;
            }
            else if (location.distanceTo(dodgeAndWeave) < 50)
            {
                Log.d("GPS_Logs", "You are at dodgeAndWeave site");
                //functionality for gps location here
                Global.currentLocation = 2;
                Global.hasBoost = true;

            }
            else if (location.distanceTo(finalFrontier) < 50)
            {
                Log.d("GPS_Logs", "You are at finalFrontier site");
                //functionality for gps location here
                Global.currentLocation = 6;
                //inc speed

            }

        }
    }
}