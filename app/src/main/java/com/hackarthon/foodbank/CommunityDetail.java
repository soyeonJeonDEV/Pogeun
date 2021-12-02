package com.hackarthon.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    String userid,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_detail);

// ListActivity 에서 넘긴 변수들을 받아줌
        seq_id = getIntent().getIntExtra("seq_id",0);
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


// 등록하기 버튼을 눌렀을 때 댓글 등록 함수 호출
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}