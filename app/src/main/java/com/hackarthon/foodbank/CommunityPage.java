package com.hackarthon.foodbank;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CommunityPage extends AppCompatActivity {

    TextView date;
    ListView communityChatList;
    ImageView communityChatBtn;
    EditText comment_et;
    String userEmail;

    long now;
    Date mdate;
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

    SharedPreferences preferences;

    ArrayList<CommunityItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        this.InitializeData();

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);

        date = (TextView) findViewById(R.id.date);
        communityChatList = (ListView) findViewById(R.id.communityChatList);
        communityChatBtn = (ImageView) findViewById(R.id.communityChatSendBtn);
        comment_et = (EditText) findViewById(R.id.communityChatSendText);

        //어댑터 연결
        CommunityAdapter adapter = new CommunityAdapter(this,items);
        communityChatList.setAdapter(adapter);

        date.setText(getTime());

        communityChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(new CommunityItem(preferences.getString("name",""), comment_et.getText().toString()));
                adapter.notifyDataSetChanged();
                communityChatList.setSelection(items.size());
                comment_et.setText("");
            }
        });


    }
    //예시
    public void InitializeData(){
        items = new ArrayList<CommunityItem>();

        items.add(new CommunityItem("test1", "푸드뱅크에서 기부하러 갈 때 주로 뭐타고 이동하시나요?"));
        items.add(new CommunityItem("test2", "딸기도 기부할 수 있나요?"));
        items.add(new CommunityItem("test3", " 저는 버스타고 이동합니다."));
    }

    //현재 날짜 받아오기
    private String getTime(){
        now = System.currentTimeMillis();
        mdate = new Date(now);
        return sdf.format(mdate);
    }

}