package com.hackarthon.foodbank;

public class CommunityItem {

    String userId;
    String content;


    public CommunityItem(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}