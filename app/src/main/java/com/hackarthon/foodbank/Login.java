package com.hackarthon.foodbank;

import android.content.Intent;
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

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = mEmailView.getText().toString();
                String userPwd = mPasswordView.getText().toString();

//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            int success = jsonResponse.getInt("code");
//                            if (success==200) {
//                                Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
//
//                                String userEmail = jsonResponse.getString("userEmail");
//                                String userPwd = jsonResponse.getString("userPwd");
                                Intent intent = new Intent(getApplicationContext(), ServiceMenu.class);
//                                // 로그인 하면서 사용자 정보 넘기기
//                                intent.putExtra("userEmail", userEmail);
//                                intent.putExtra("userPwd", userPwd);
                                startActivity(intent);
                                finish();
//
//                            } else {
//                                Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                LoginRequest loginRequest = new LoginRequest(userEmail, userPwd, responseListener);
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

    @Override
    public void onBackPressed() {

    }


}