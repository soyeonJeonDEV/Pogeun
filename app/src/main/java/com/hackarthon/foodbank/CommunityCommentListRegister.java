package com.hackarthon.foodbank;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CommunityCommentListRegister extends StringRequest {

    final static private String URL = "http://ec2-54-180-46-45.ap-northeast-2.compute.amazonaws.com:3000/community/comment_list"; // "http:// 퍼블릭 DSN 주소/Login.php";
    private Map<String,String> parameters;

    public CommunityCommentListRegister(String seq_id,  Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        //parameters.put("db컬럼명", );
        parameters.put("community_id", String.valueOf(seq_id));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}