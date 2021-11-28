package com.hackarthon.foodbank;


import android.os.Bundle;


import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;


public class CommunityPage extends AppCompatActivity {

    ListView communityChatList;
    ImageView communityChatBtn;
    EditText comment_et;

    String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        communityChatList = (ListView) findViewById(R.id.communityChatList);
        communityChatBtn = (ImageView) findViewById(R.id.communityChatSendBtn);


        comment_et = (EditText) findViewById(R.id.communityChatSendText);

        userEmail = getIntent().getStringExtra("userEmail");



    }

}