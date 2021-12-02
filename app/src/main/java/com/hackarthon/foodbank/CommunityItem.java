package com.hackarthon.foodbank;

public class CommunityItem {

    String user_id;
    String content;
    int seq_id;
    String created_at;



    public CommunityItem(String userId, String content, int seq_id,String created_at) {
        this.user_id = userId;
        this.content = content;
        this.seq_id = seq_id;
        this.created_at = created_at;

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

    public String getCreated_at() {
        return created_at;
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

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}