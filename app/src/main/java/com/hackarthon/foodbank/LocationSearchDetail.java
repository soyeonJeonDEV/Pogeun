package com.hackarthon.foodbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationSearchDetail extends AppCompatActivity implements OnMapReadyCallback {

    // 구글 맵 참조변수 생성
    GoogleMap mMap;
    TextView shop_name_, tel_, addr_, article;
    double lat, lng;
    String shop;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search_detail);

//        jsonParsing(getJsonString());

        // SupportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출한다.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //getMapAsync must be called on the main thread.


        shop_name_ = (TextView) findViewById(R.id.shop_name_);
        tel_ = (TextView) findViewById(R.id.tel_);

        addr_ = (TextView) findViewById(R.id.addr_);
        article = (TextView) findViewById(R.id.article);

        shop_name_.setText(getIntent().getStringExtra("shop_name"));
        tel_.setText(getIntent().getStringExtra("tel"));
        addr_.setText(getIntent().getStringExtra("addr"));
        shop = getIntent().getStringExtra("shop_name");
        lat = getIntent().getDoubleExtra("lat", 0);
        lng = getIntent().getDoubleExtra("lng", 0);
        String art = getIntent().getStringExtra("article");
        if(art != null){
            article.setText(getIntent().getStringExtra("article"));
        }else{
            article.setText("없음");
        }



        SpannableString content = new SpannableString(getIntent().getStringExtra("tel"));
        content.setSpan(new UnderlineSpan(), 0, getIntent().getStringExtra("tel").length(), 0);
        tel_.setText(content);


        String tel_action = "tel:" + getIntent().getStringExtra("tel");

        tel_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(tel_action));
                startActivity(intent);
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 구글 맵 객체를 불러온다.
        mMap = googleMap;

        LatLng seoul = new LatLng(lat, lng);

        // 구글 맵에 표시할 마커에 대한 옵션 설정
        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(seoul)
                .title(shop);

        // 마커를 생성한다.
        mMap.addMarker(makerOptions);

        //카메라를 여의도 위치로 옮긴다.
        //   mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15));
    }

//    private String getJsonString()
//    {
//        String json = "";
//
//        try {
//            InputStream is = getAssets().open("test.json");
//            int fileSize = is.available();
//
//            byte[] buffer = new byte[fileSize];
//            is.read(buffer);
//            is.close();
//
//            json = new String(buffer, "UTF-8");
//        }
//        catch (IOException ex)
//        {
//            ex.printStackTrace();
//        }
//
//        return json;
//    }
//
//    private void jsonParsing(String json)
//    {
//
//        try{
//            JSONObject jsonObject = new JSONObject(json);
//
//            JSONArray movieArray = jsonObject.getJSONArray("shop");
//
//            for(int i=0; i<movieArray.length(); i++)
//            {
//                JSONObject movieObject = movieArray.getJSONObject(i);
//
//                 lat = movieObject.getDouble("lat");
//                 lng = movieObject.getDouble("lng");
//
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
    //   }
}
