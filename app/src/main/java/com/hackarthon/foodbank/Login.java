package com.hackarthon.foodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class Login extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button loginBtn;
    private Button singupPageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.idEdit);
        mPasswordView = (EditText) findViewById(R.id.pwEdit);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        singupPageBtn = (Button) findViewById(R.id.singupPageBtn);

        SharedPreferences pref = getSharedPreferences("id",MODE_PRIVATE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                //로그인 확인
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            int success = jsonResponse.getInt("code");
//                            if (success==200) {
//                                Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
//
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("userid",user_id);
                                editor.commit();

                                Intent intent = new Intent(getApplicationContext(), ServiceMenu.class);
                                startActivity(intent);
                                finish();
//
//                            } else if (success == 10 ){
//                                Toast.makeText(getApplicationContext(), "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
//                                return;
//                            } else if (success == 11 ){
//                                Toast.makeText(getApplicationContext(), "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
//                                return;
//                            } else{
//                                Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(),
//                                    "Error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    }
//                };
//
//                //LoginRequest.java 에서 url 연결
//                LoginRequest loginRequest = new LoginRequest(user_id, password, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(Login.this);
//                queue.add(loginRequest);
            }
        });

        singupPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(), Signup.class);
                startActivity(signup);
                finish();
            }
        });
    }
//
//    @Override
//    public void onBackPressed() {
//
//    }


}