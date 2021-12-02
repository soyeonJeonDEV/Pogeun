package com.hackarthon.foodbank;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://ec2-15-164-177-241.ap-northeast-2.compute.amazonaws.com:3000/user/login"; // "http:// 퍼블릭 DSN 주소/Login.php";
    private Map<String, String> parameters;

    public LoginRequest(String user_id, String password, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
        parameters.put("password", password);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}