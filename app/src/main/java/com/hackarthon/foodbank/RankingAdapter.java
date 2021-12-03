package com.hackarthon.foodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RankingAdapter extends BaseAdapter {

    private TextView rank, r_name, r_donation;
    ArrayList<RankingItem> items = new ArrayList<>();

    public RankingAdapter(){};

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(RankingItem item){
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
            convertView = inflater.inflate(R.layout.ranking_item,parent,false);
        }
        rank = convertView.findViewById(R.id.rank);
        r_name = convertView.findViewById(R.id.r_name);
        r_donation = convertView.findViewById(R.id.r_donation);

        RankingItem item = items.get(position);

        rank.setText(String.valueOf(item.getRank()) + "위.  ");
        r_name.setText(item.getR_name() + "님  ");
        r_donation.setText(item.getR_donation() + "원");

        return convertView;
    }
}
