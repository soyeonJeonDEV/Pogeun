package com.hackarthon.foodbank;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class RankingPage extends AppCompatActivity {
    TextView r1_name, r1_donation, r2_name, r2_donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        r1_name = findViewById(R.id.r1_name);
        r1_donation = findViewById(R.id.r1_donation);
        r2_name = findViewById(R.id.r2_name);
        r2_donation = findViewById(R.id.r2_donation);

        sendRequest();

    }
    public void sendRequest() {
        // Request를 보낼 queue를 생성한다. 필요시엔 전역으로 생성해 사용하면 된다.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ec2-54-180-46-45.ap-northeast-2.compute.amazonaws.com:3000/donate/rank";

        // StringRequest를 보낸다.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray movieArray = jsonObject.getJSONArray("list");


                            for (int i = 0; i < movieArray.length(); i++) {
                                JSONObject movieObject = movieArray.getJSONObject(i);
                                int rank = movieObject.getInt("rank");
                                String name = movieObject.getString("user_id");
                                String donation = movieObject.getString("donate_sum");

                                if(rank == 1){
                                    r1_name .setText(name);
                                    r1_donation .setText(donation + "원");
                                }else if (rank == 2){
                                    r2_name.setText(name);
                                    r2_donation.setText(donation + "원");
                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // RequestQueue에 현재 Task를 추가해준다.
        queue.add(stringRequest);
    }

}