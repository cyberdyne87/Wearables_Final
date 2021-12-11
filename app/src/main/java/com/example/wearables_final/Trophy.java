package com.example.wearables_final;

import static com.example.wearables_final.R.drawable.trophyroom_graybadges;
import static com.example.wearables_final.R.drawable.trophyroom_graybadges_color_contellationdiscoverer;
import static com.example.wearables_final.R.drawable.trophyroom_graybadges_color_dodgeweave;
import static com.example.wearables_final.R.drawable.trophyroom_graybadges_color_eventhorizon;
import static com.example.wearables_final.R.drawable.trophyroom_graybadges_color_finalfrontier;
import static com.example.wearables_final.R.drawable.trophyroom_graybadges_color_lunarlander;
import static com.example.wearables_final.R.drawable.trophyroom_graybadges_color_spaceexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Trophy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);

        Log.d("GPS_Logs", "Current Location: " + Global.currentLocation);

        ImageView trophyView = findViewById(R.id.trophyImgView);
        if (Global.currentLocation == 0) trophyView.setImageResource(trophyroom_graybadges);
        else if (Global.currentLocation == 1)
            trophyView.setImageResource(trophyroom_graybadges_color_lunarlander);
        else if (Global.currentLocation == 2)
            trophyView.setImageResource(trophyroom_graybadges_color_dodgeweave);
        else if (Global.currentLocation == 3)
            trophyView.setImageResource(trophyroom_graybadges_color_contellationdiscoverer);
        else if (Global.currentLocation == 4)
            trophyView.setImageResource(trophyroom_graybadges_color_spaceexplorer);
        else if (Global.currentLocation == 5)
            trophyView.setImageResource(trophyroom_graybadges_color_eventhorizon);
        else if (Global.currentLocation == 6)
            trophyView.setImageResource(trophyroom_graybadges_color_finalfrontier);

    }
    /*
    public void setImg (int currLoc)

    {
        ImageView trophyView = findViewById(R.id.trophyImgView);

        if (currLoc == 1)
            trophyView.setImageResource(trophyroom_graybadges_color_lunarlander);
        else if (currLoc == 2)
            trophyView.setImageResource(trophyroom_graybadges_color_dodgeweave);
        else if (currLoc == 3)
            trophyView.setImageResource(trophyroom_graybadges_color_contellationdiscoverer);
        else if (currLoc == 4)
            trophyView.setImageResource(trophyroom_graybadges_color_spaceexplorer);
        else if (currLoc == 5)
            trophyView.setImageResource(trophyroom_graybadges_color_eventhorizon);
        else if (currLoc == 6)
            trophyView.setImageResource(trophyroom_graybadges_color_finalfrontier);

    }
    */


}
