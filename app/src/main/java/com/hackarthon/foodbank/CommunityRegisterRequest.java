package com.hackarthon.foodbank;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommunityRegisterRequest  extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://192.168.0.43:3000/community/reg"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;


    public CommunityRegisterRequest(String userid, String content, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("user_id", userid);
        parameters.put("text", content);

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}