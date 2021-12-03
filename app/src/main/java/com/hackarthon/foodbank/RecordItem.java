package com.hackarthon.foodbank;

public class RecordItem {
    String center_name;
    int record_donation;

    public RecordItem(String center_name, int record_donation){
        this.center_name = center_name;
        this.record_donation = record_donation;
    }

    public String getCenter_name() {
        return center_name;
    }

    public int getRecord_donation() {
        return record_donation;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public void setRecord_donation(int record_donation) {
        this.record_donation = record_donation;
    }
}
