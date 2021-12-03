package com.hackarthon.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CommunityDetail extends AppCompatActivity {

    // 로그에 사용할 TAG
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    TextView co_name, content_tv, co_date;
    LinearLayout comment_layout;
    EditText comment_et;
    Button reg_button;

    // 선택한 게시물의 번호
    int seq_id;

    // 유저아이디 변수
    String userid, content, comm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_detail);

// ListActivity 에서 넘긴 변수들을 받아줌
        seq_id = getIntent().getIntExtra("seq_id", 0);
        userid = getIntent().getStringExtra("userid");
        content = getIntent().getStringExtra("content");
// 컴포넌트 초기화
        co_name = findViewById(R.id.co_name);
        content_tv = findViewById(R.id.content_tv);

        comment_layout = findViewById(R.id.comment_layout);
        comment_et = findViewById(R.id.comment_et);
        reg_button = findViewById(R.id.reg_button);

        co_name.setText(userid);
        content_tv.setText(content);

        Load();


// 등록하기 버튼을 눌렀을 때 댓글 등록 함수 호출
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("id", MODE_PRIVATE);
                userid = pref.getString("userid", "");
                comm = comment_et.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 게시판 등록 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);

                            int success = jsonResponse.getInt("code");
                            if (success == 200) {
                                Load();
                                Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
                                comment_et.setText("");
                            } else {
                                Toast.makeText(getApplicationContext(), "등록 실패했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분(CommunityRegisterRequest.java에서 통신 구현)
                CommunityCommentRegister communityCommentRegister = new CommunityCommentRegister(String.valueOf(seq_id), userid, comm, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CommunityDetail.this);
                queue.add(communityCommentRegister);
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
                        comment_layout.removeAllViews();


                        JSONArray jsonArray = jsonResponse.getJSONArray("list");


// custom_comment 를 불러오기 위한 객체
                        LayoutInflater layoutInflater = LayoutInflater.from(CommunityDetail.this);

                        for (int i = 0; i < jsonArray.length(); i++) {

// custom_comment 의 디자인을 불러와서 사용
                            View customView = layoutInflater.inflate(R.layout.community_comment, null);
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            userid = jsonObject.optString("user_id");
                            comm = jsonObject.optString("text");

                            ((TextView) customView.findViewById(R.id.cmt_userid_tv)).setText(userid);
                            ((TextView) customView.findViewById(R.id.cmt_content_tv)).setText(comm);

// 댓글 레이아웃에 custom_comment 의 디자인에 데이터를 담아서 추가
                            comment_layout.addView(customView);
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
        CommunityCommentListRegister communityCommentListRegister = new CommunityCommentListRegister(String.valueOf(seq_id), responseListener);
        RequestQueue queue = Volley.newRequestQueue(CommunityDetail.this);
        queue.add(communityCommentListRegister);
    }


}