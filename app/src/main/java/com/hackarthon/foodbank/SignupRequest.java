package com.hackarthon.foodbank;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupRequest extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private String URL = "http://ec2-54-180-46-45.ap-northeast-2.compute.amazonaws.com:3000/user/create"; // "http:// 퍼블릭 DNS 주소/Register.php"
    private Map<String, String> parameters;


    public SignupRequest(String user_id, String password, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        //parameter.put("db_컬럼명",);
        parameters.put("user_id", user_id);
        parameters.put("password", password);

    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}