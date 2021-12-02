package com.hackarthon.foodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CommunityAdapter extends BaseAdapter {

    TextView user_id, content;
    ArrayList<CommunityItem> items = new ArrayList<CommunityItem>();
    public CommunityAdapter(){};

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(CommunityItem item){
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.community_item,parent,false);
        }
        user_id = convertView.findViewById(R.id.user_id);
        content = convertView.findViewById(R.id.content);


        CommunityItem item = items.get(position);

        user_id.setText(item.getUser_id());
        content.setText(item.getContent());

        return convertView;
    }
}
