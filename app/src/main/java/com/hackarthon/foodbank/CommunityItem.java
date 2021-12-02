package com.hackarthon.foodbank;

public class CommunityItem {

    String user_id;
    String content;
    int seq_id;


    public CommunityItem(String userId, String content, int seq_id) {
        this.user_id = userId;
        this.content = content;
        this.seq_id = seq_id;

    }

    public String getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public int getSeq_id() {
        return seq_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSeq_id(int seq_id) {
        this.seq_id = seq_id;
    }
}