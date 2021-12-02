package com.hackarthon.foodbank;


import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import javax.net.ssl.HttpsURLConnection;


public class CommunityPage extends AppCompatActivity {

    // 로그에 사용할 TAG 변수
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    ListView listView;
    Button regBtn;
    String user_id, content,created_at;
    int seq_id;
    CommunityAdapter adapter;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        listView = findViewById(R.id.communityList);
        regBtn = findViewById(R.id.regBtn);

        sendRequest();

        adapter = new CommunityAdapter();
        listView.setAdapter(adapter);



// listView 를 클릭했을 때 이벤트 추가
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CommunityItem item = (CommunityItem) parent.getItemAtPosition(position);
// 게시물의 번호와 userid를 가지고 DetailActivity 로 이동
                Intent intent = new Intent(CommunityPage.this, CommunityDetail.class);
                intent.putExtra("userid",item.getUser_id());
                intent.putExtra("seq_id",item.getSeq_id());
                intent.putExtra("content",item.getContent());
                startActivity(intent);

            }
        });


// 버튼 이벤트 추가
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CommunityPage.this, CommunityRegister.class);
                startActivity(intent);
            }
        });
    }

    public void sendRequest() {
        // Request를 보낼 queue를 생성한다. 필요시엔 전역으로 생성해 사용하면 된다.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.5:3000/community/list";//url

        // StringRequest를 보낸다.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //GET통신으로 받아옴
                            JSONArray movieArray = jsonObject.getJSONArray("result"); // table 이름

                            for (int i = 0; i < movieArray.length(); i++) {
                                JSONObject movieObject = movieArray.getJSONObject(i);

                                user_id = movieObject.getString("user_id");//컬럼명
                                content = movieObject.getString("text");
                                seq_id = movieObject.getInt("community_id");
                                created_at = movieObject.getString("create_dt");

                                adapter.addItem(new CommunityItem(user_id,content,seq_id,created_at));
                                adapter.notifyDataSetChanged();

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