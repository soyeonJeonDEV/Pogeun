package com.hackarthon.foodbank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationSearchAdapter extends BaseAdapter {

    private TextView shop_name,addr,tel;

    ArrayList<LocationSearchItem> items = new ArrayList<LocationSearchItem>();

    public LocationSearchAdapter(){};

    @Override
    public int getCount() {
        return items.size();
    }
    public void addItem(LocationSearchItem item){
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
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.location_search_item,parent,false);
        }
        shop_name = convertView.findViewById(R.id.shop_name);
        addr = convertView.findViewById(R.id.addr);
        tel = convertView.findViewById(R.id.tel);

        LocationSearchItem item = items.get(position);

        shop_name.setText(item.getShop_name());
        addr.setText(item.getAddr());
        tel.setText(item.getTel());



        return convertView;
    }
}
