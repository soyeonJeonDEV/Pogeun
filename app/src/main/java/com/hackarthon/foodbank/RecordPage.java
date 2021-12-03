package com.hackarthon.foodbank;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RecordPage extends AppCompatActivity {

    ListView listView;
    String center_name;
    int record_donation;
    String user_id;
    ArrayList<RecordItem> items;
    RecordAdapter adapter;
    TextView record_id,record_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_list);

        listView = findViewById(R.id.recod_list);
        record_id = findViewById(R.id.record_id);
        record_count = findViewById(R.id.record_count);


        SharedPreferences pref = getSharedPreferences("id", MODE_PRIVATE);
        user_id = pref.getString("userid", "");

        Load();

        record_id.setText(user_id + "님의 기부 이력입니다.");




        adapter = new RecordAdapter();
        listView.setAdapter(adapter);

    }

    public void Load() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    int success = jsonResponse.getInt("code");
                    String count = jsonResponse.getString("donate_sum");

                    record_count.setText("총 기부금액 " + count + "원");

                    if (success == 200) {
                        JSONArray jsonArray = jsonResponse.getJSONArray("list");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            center_name = jsonObject.optString("center_name");
                            record_donation = jsonObject.optInt("donate_amount");

                            adapter.addItem(new RecordItem(center_name,record_donation));
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
        RecordPageRegister recordPageRegister = new RecordPageRegister(user_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RecordPage.this);
        queue.add(recordPageRegister);

    }
}
