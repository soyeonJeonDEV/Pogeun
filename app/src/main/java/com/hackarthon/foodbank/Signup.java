package com.hackarthon.foodbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;



public class Signup extends Activity {


    private AutoCompleteTextView signup_idEdit;
    private EditText signup_pwEdit;
    private Button signupBtn;
    private Button loginPageBtn;
    private static String user_id, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signup_idEdit = (AutoCompleteTextView) findViewById(R.id.signup_idEdit);
        signup_pwEdit = (EditText)findViewById(R.id.signup_pwEdit);
        signupBtn = (Button) findViewById(R.id.signupBtn);
        loginPageBtn = (Button) findViewById(R.id.loginPageBtn);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_id = signup_idEdit.getText().toString();
                password = signup_pwEdit.getText().toString();

                // 회원가입 절차 시작
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try{
                            // String으로 그냥 못 보냄으로 JSON Object 형태로 변형하여 전송
                            // 서버 통신하여 회원가입 성공 여부를 jsonResponse로 받음
                            JSONObject jsonResponse = new JSONObject(response);

                            int success = jsonResponse.getInt("code");
                            if(success == 200){ // 회원가입이 가능한다면
                                Intent intent = new Intent(Signup.this, SignupPopuop.class );
                                startActivity(intent);

                            }else if (success == 10){// 회원가입이 안된다면
                                Toast.makeText(getApplicationContext(), "아이디가 이미 존재합니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }else{
                                Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다. 다시 한 번 확인해 주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        catch(Exception e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                };

                // Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분(SignupRequest.java에서 서버와 통신 구현)
                SignupRequest signupRequest = new SignupRequest(user_id,password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Signup.this);
                queue.add(signupRequest);



            }
        });
        loginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getApplicationContext(),Login.class);
                startActivity(login);
                finish();
            }
        });
    }



}