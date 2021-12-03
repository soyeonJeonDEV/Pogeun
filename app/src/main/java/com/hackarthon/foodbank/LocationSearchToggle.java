package com.hackarthon.foodbank;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LocationSearchToggle extends AppCompatActivity {

    TextView bank_name;
    String[] titles = {"전국", "중앙", "서울", "부산", "대구", "인천", "광주", "대전", "울산", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주", "세종"};
    ListView locationList;
    LocationSearchAdapter adapter;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search_toggle);

        bank_name = findViewById(R.id.bank_name);
        Spinner toggle = findViewById(R.id.toggle);
        locationList = (ListView) findViewById(R.id.toggleList);

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
                Intent pop = new Intent(getApplicationContext(), LocationSearchDetail.class);
                pop.putExtras(extras);
                startActivity(pop);
            }
        });



        ArrayAdapter<String> adapter_toggle = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, titles);
        adapter_toggle.setDropDownViewResource( R.layout.support_simple_spinner_dropdown_item);
        toggle.setAdapter(adapter_toggle);

        toggle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bank_name.setText(titles[position] + " 푸드뱅크 목록");
                title = titles[position];

                adapter = new LocationSearchAdapter();
                Load();
                locationList.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bank_name.setText("지역을 선택해 주세요");
            }
        });


    }

    public void Load(){
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

                            String s = jsonObject.getString("name");
                            String x = jsonObject.getString("addr");
                            String y = jsonObject.getString("tel");
                            double lat = jsonObject.getDouble("y");
                            double lng = jsonObject.getDouble("x");


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
        LocationSearchToggleRequest locationSearchToggleRequest = new LocationSearchToggleRequest(title, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LocationSearchToggle.this);
        queue.add(locationSearchToggleRequest);
    }

}