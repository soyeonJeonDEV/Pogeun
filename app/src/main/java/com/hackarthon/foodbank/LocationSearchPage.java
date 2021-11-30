package com.hackarthon.foodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import noman.googleplaces.Place;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LocationSearchPage extends AppCompatActivity {

    EditText location_et;
    Button search_btn;
    ListView locationList;
    ArrayList<LocationSearchItem> items;
    LocationSearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search);

        locationList = (ListView) findViewById(R.id.locationList);

        adapter = new LocationSearchAdapter();
        adapter.addItem(new LocationSearchItem("안산시광림푸드뱅크","경기도 안산시 단원구 예술1길 6","031-487-1377"));
        locationList.setAdapter(adapter);
        location_et = (EditText) findViewById(R.id.location_et);
        search_btn = (Button) findViewById(R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_et.setText("");
            }
        });

        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocationSearchItem item = (LocationSearchItem) parent.getItemAtPosition(position);
                Bundle extras = new Bundle();
                extras.putString("shop_name",item.getShop_name());
                extras.putString("addr",item.getAddr());
                extras.putString("tel",item.getTel());
                Intent pop = new Intent(getApplicationContext(), LocationSearchDetail.class);
                pop.putExtras(extras);
                startActivity(pop);
            }
        });
    }
}