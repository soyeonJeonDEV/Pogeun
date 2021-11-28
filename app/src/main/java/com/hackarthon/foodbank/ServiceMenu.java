package com.hackarthon.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

public class ServiceMenu extends AppCompatActivity {
    private long backKeyPressedTime = 0;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_menu);

        Button locationBtn = (Button) findViewById(R.id.locationBtn);
        Button recordBtn = (Button) findViewById(R.id.recordBtn);
        Button guideBtn = (Button) findViewById(R.id.guideBtn);
        Button communityBtn = (Button) findViewById(R.id.communityBtn);
        Button statisticscheckBtn = (Button) findViewById(R.id.statisticscheckBtn);

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent locate = new Intent(getApplicationContext(), LocationSearchPage.class);
                startActivity(locate);
            }
        });
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recordPage = new Intent(getApplicationContext(), RecordPage.class);
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
        statisticscheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StatisticsCheckPage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }
}