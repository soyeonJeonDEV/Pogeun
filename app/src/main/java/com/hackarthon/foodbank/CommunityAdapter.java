package com.hackarthon.foodbank;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CommunityAdapter extends BaseAdapter {
    ArrayList<CommunityItem> items;
    Context context;
    SharedPreferences preferences;

    public CommunityAdapter(Context context,ArrayList<CommunityItem> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    public void addItem(CommunityItem item){
        items.add(item);
    }



    @Override
    public CommunityItem getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        converView = inflater.inflate(R.layout.community_chat,null);

        TextView communityItemUser = converView.findViewById(R.id.communityItemUser);
        TextView communityItemMsg = converView.findViewById(R.id.communityItemMsg);

        CommunityItem item = items.get(position);
        communityItemUser.setText(item.userEmail);
        communityItemMsg.setText(item.comment);

        preferences = context.getSharedPreferences("pref",Context.MODE_PRIVATE);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) communityItemMsg.getLayoutParams();
        RelativeLayout.LayoutParams paramsUser = (RelativeLayout.LayoutParams) communityItemUser.getLayoutParams();

        //로그인한 name값이 같으면 채팅창을 오른쪽으로
        if(items.get(position).userEmail == preferences.getString("name","")){
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
            paramsUser.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
            communityItemMsg.setBackgroundResource(R.drawable.chat_left);
            //name이 다르면 채팅창을 왼쪽으로
        }else{
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
            paramsUser.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
            communityItemMsg.setBackgroundResource(R.drawable.chat_right);
        }





        return converView;

    }



}


