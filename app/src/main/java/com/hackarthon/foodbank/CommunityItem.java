package com.hackarthon.foodbank;

public class CommunityItem {

    String userEmail;
    String comment;


    public CommunityItem(String userEmail, String comment) {
        this.userEmail = userEmail;
        this.comment = comment;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getComment() {
        return comment;
    }

}