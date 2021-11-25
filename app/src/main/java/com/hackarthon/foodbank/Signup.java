package com.hackarthon.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        EditText signup_idEdit = (EditText)findViewById(R.id.signup_idEdit);
        EditText signup_pwEdit = (EditText)findViewById(R.id.signup_pwEdit);
        Button signupBtn = (Button) findViewById(R.id.signupBtn);
        Button loginPageBtn = (Button) findViewById(R.id.loginPageBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(),Login.class);
                startActivity(signup);
                finish();
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