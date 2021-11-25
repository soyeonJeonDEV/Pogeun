package com.hackarthon.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText idEdit = (EditText)findViewById(R.id.idEdit);
        EditText pwEdit = (EditText)findViewById(R.id.pwEdit);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        Button singupPageBtn = (Button) findViewById(R.id.singupPageBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(),ServiceMenu.class);
                startActivity(back);
                finish();
            }
        });
        singupPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(getApplicationContext(),Signup.class);
                startActivity(signup);
                finish();
            }
        });
    }
}