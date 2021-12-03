package com.hackarthon.foodbank;

public class RankingItem {
    String rank;
    String r_name;
    String r_donation;
    public RankingItem(String rank, String r_name, String r_donation){
        this.rank = rank;
        this.r_name = r_name;
        this.r_donation = r_donation;
    }

    public String getRank() {
        return rank;
    }

    public String getR_name() {
        return r_name;
    }

    public String getR_donation() {
        return r_donation;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public void setR_donation(String r_donation) {
        this.r_donation = r_donation;
    }
}

