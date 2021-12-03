package com.hackarthon.foodbank;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommunityCommentRegister extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://ec2-54-180-46-45.ap-northeast-2.compute.amazonaws.com:3000/community/comment_create"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;


    public CommunityCommentRegister(String community_id, String userid, String content, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("community_id", community_id);
        parameters.put("user_id", userid);
        parameters.put("text", content);

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}