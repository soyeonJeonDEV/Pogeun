package com.hackarthon.foodbank;

public class LocationSearchItem {
    String shop_name;
    String addr;
    String tel;
    double lat;
    double lng;

    public LocationSearchItem(String shop_name, String addr, String tel) {
        this.shop_name = shop_name;
        this.addr = addr;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getAddr() {
        return addr;
    }

    public String getTel() {
        return tel;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

//    @Override
//    public String toString() {
//        return "LocationSearchItem{" + "shop_name='" + shop_name + '\'' +
//                ", addr='" + addr + '\'' + ", tel='" + tel + '\'' + '}';
//    }
}
