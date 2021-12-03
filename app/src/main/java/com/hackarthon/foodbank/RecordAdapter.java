package com.hackarthon.foodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordAdapter extends BaseAdapter {

    private TextView center_name, record_donation;

    ArrayList<RecordItem> items = new ArrayList<>();

    public RecordAdapter(){};

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(RecordItem item) {
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
            convertView = inflater.inflate(R.layout.record_item,parent,false);
        }
        center_name = convertView.findViewById(R.id.center_name);
        record_donation = convertView.findViewById(R.id.record_donation);

        RecordItem item = items.get(position);

        center_name.setText(item.getCenter_name());
        record_donation.setText(item.getRecord_donation() + "원");

        return convertView;
    }
}
