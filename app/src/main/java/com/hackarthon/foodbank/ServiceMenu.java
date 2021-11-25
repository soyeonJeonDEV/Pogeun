package com.hackarthon.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

public class ServiceMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_menu);

        Button locationBtn = (Button) findViewById(R.id.locationBtn);
        Button recordBtn = (Button) findViewById(R.id.recordBtn);
        Button guideBtn = (Button) findViewById(R.id.guideBtn);
        Button communityBtn = (Button) findViewById(R.id.communityBtn);
        Button loginPageBtn = (Button) findViewById(R.id.loginPageBtn);

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent locate = new Intent(getApplicationContext(),LocationSearchPage.class);
                startActivity(locate);
            }
        });
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recordPage = new Intent(getApplicationContext(),RecordPage.class);
                startActivity(recordPage);
            }
        });
        guideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent guidePage = new Intent(getApplicationContext(), GuidePage.class);
                startActivity(guidePage);
            }
        });
        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent communityPage = new Intent(getApplicationContext(), CommunityPage.class);
                startActivity(communityPage);
            }
        });
        loginPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginPage = new Intent(getApplicationContext(),Login.class);
                startActivity(loginPage);
                finish();
            }
        });
    }
}