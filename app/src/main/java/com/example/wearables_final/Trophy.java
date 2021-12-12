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
import android.widget.TextView;

import org.w3c.dom.Text;

public class Trophy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);

        TextView hint = findViewById(R.id.nextLocHint);
        TextView hintHelper = findViewById(R.id.Hintstatic);

        Log.d("GPS_Logs", "Current Location: " + Global.currentLocation);

        ImageView trophyView = findViewById(R.id.trophyImgView);
        if (Global.currentLocation == 0)
        {
            trophyView.setImageResource(trophyroom_graybadges);
            hint.setText("Once known as Moon, SD");
        }
        else if (Global.currentLocation == 1)
        {
            trophyView.setImageResource(trophyroom_graybadges_color_lunarlander);
            hint.setText("Old Textile Mill in ME");
        }
        else if (Global.currentLocation == 2)
        {
            trophyView.setImageResource(trophyroom_graybadges_color_dodgeweave);
            hint.setText("Island off of Greece - Water Serpent");
        }
        else if (Global.currentLocation == 3)
        {
            trophyView.setImageResource(trophyroom_graybadges_color_contellationdiscoverer);
            hint.setText("Where Rockets leave Earth");
        }
        else if (Global.currentLocation == 4)
        {
            trophyView.setImageResource(trophyroom_graybadges_color_spaceexplorer);
            hint.setText("Star Gazing Center in the Pacific");
        }
        else if (Global.currentLocation == 5)
        {
            trophyView.setImageResource(trophyroom_graybadges_color_eventhorizon);
            hint.setText("60.1648 , -141.7477");
        }
        else if (Global.currentLocation == 6)
        {
            trophyView.setImageResource(trophyroom_graybadges_color_finalfrontier);
            hintHelper.setText("No Further Assignments");
            hint.setText("Great Job!");
        }

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
