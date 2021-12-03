package com.hackarthon.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LocationSearchMenu extends AppCompatActivity {

    Button locationGps, location_toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search_menu);

        locationGps = findViewById(R.id.locationGps);
        location_toggle = findViewById(R.id.location_toggle);

        locationGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gps = new Intent(getApplicationContext(), LocationSearchPage.class);
                startActivity(gps);
            }
        });

        location_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toggle = new Intent(getApplicationContext(), LocationSearchToggle.class);
                startActivity(toggle);
            }
        });

    }
}