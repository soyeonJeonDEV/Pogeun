package com.hackarthon.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class CommunityRegister extends AppCompatActivity {

    // 로그에 사용할 TAG 변수 선언
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText content_et;
    Button reg_button;
    ArrayList<CommunityItem> items;
    CommunityAdapter adapter;

    // 유저아이디 변수
    String userid;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_register);



// 컴포넌트 초기화

        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

// 버튼 이벤트 추가
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Login 에서 넘긴 userid 를 변수로 받음
                SharedPreferences pref = getSharedPreferences("id", MODE_PRIVATE);
                userid = pref.getString("userid", "");
                content = content_et.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);

                            int success = jsonResponse.getInt("code");
                            if(success == 200){
                                Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(CommunityRegister.this, CommunityPage.class );
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "등록 실패했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                CommunityRegisterRequest communityRegisterRequest = new CommunityRegisterRequest(userid,content, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CommunityRegister.this);
                queue.add(communityRegisterRequest);
                finish();

            }

        });

    }


}