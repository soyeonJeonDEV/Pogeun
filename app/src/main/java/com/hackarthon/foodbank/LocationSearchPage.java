package com.hackarthon.foodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LocationSearchPage extends AppCompatActivity {


    Button search_btn;
    ListView locationList;
    ArrayList<LocationSearchItem> items;
    LocationSearchAdapter adapter;
    String latitude;
    String longitude;
    EditText location_et;
    String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search);

        locationList = (ListView) findViewById(R.id.locationList);

//        search_btn = (Button) findViewById(R.id.search_btn);
//
//        location_et = (EditText) findViewById(R.id.location_et);



        adapter = new LocationSearchAdapter();
        locationList.setAdapter(adapter);


        latitude = getIntent().getStringExtra("lat");
        longitude = getIntent().getStringExtra("lng");


        MyLocationLoad();


//        search_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                location = location_et.getText().toString();
//                if (location == null){
//                    Toast.makeText(getApplicationContext(),"지역을 입력하세요",Toast.LENGTH_SHORT ).show();
//                }else {
//                    SearchLoad();
//                    adapter = new LocationSearchAdapter();
//                    locationList.setAdapter(adapter);
//                }
//            }
//        });

        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocationSearchItem item = (LocationSearchItem) parent.getItemAtPosition(position);
                Bundle extras = new Bundle();
                extras.putString("shop_name", item.getShop_name());
                extras.putString("addr", item.getAddr());
                extras.putString("tel", item.getTel());
                extras.putDouble("lat", item.getLat());
                extras.putDouble("lng", item.getLng());
                extras.putString("article", item.getArticle());
                Intent pop = new Intent(getApplicationContext(), LocationSearchDetail.class);
                pop.putExtras(extras);
                startActivity(pop);
            }
        });
    }

    public void MyLocationLoad() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int success = jsonResponse.getInt("code");
                    if (success == 200) {
                        JSONArray jsonArray = jsonResponse.getJSONArray("list");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String name = jsonObject.getString("office");
                            String addr = jsonObject.getString("detail_office");
                            String tel = jsonObject.getString("phone_number");
                            String lat = jsonObject.getString("y");
                            String lng = jsonObject.getString("x");
                            String article = jsonObject.getString("donate_article");

                            adapter.addItem(new LocationSearchItem(name, addr, tel, Double.parseDouble(lat), Double.parseDouble(lng), article));
                            adapter.notifyDataSetChanged();

                        }
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        //CommunityCommentListRegister.java 에서 url 연결
        LocationSearchRequest locationSearchRequest = new LocationSearchRequest(longitude, latitude, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LocationSearchPage.this);
        queue.add(locationSearchRequest);
    }


    public void SearchLoad(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int success = jsonResponse.getInt("code");
                    if (success == 200) {

                        JSONArray jsonArray = jsonResponse.getJSONArray("list");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String name = jsonObject.getString("office");
                            String addr = jsonObject.getString("detail_office");
                            String tel = jsonObject.getString("phone_number");
                            String lat = jsonObject.getString("y");
                            String lng = jsonObject.getString("x");
                            String article = jsonObject.getString("donate_article");

                            adapter.addItem(new LocationSearchItem(name, addr, tel,Double.parseDouble(lat) ,Double.parseDouble(lng), article));
                            adapter.notifyDataSetChanged();

                        }
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        //CommunityCommentListRegister.java 에서 url 연결
        LocationSearchToggleRequest locationSearchToggleRequest = new LocationSearchToggleRequest(location, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LocationSearchPage.this);
        queue.add(locationSearchToggleRequest);
    }

}




