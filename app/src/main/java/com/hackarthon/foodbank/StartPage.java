package com.hackarthon.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        Button sendBtn = (Button) findViewById(R.id.sendBtn);
        Button getBtn = (Button) findViewById(R.id.getBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendPage = new Intent(getApplicationContext(),ServiceMenu.class);
                startActivity(sendPage);
            }
        });
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getPage = new Intent(getApplicationContext(),ServiceMenu.class);
                startActivity(getPage);
            }
        });
    }
}