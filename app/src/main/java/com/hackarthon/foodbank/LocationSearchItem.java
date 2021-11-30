package com.hackarthon.foodbank;

public class LocationSearchItem {
    String shop_name;
    String addr;
    String tel;

    public LocationSearchItem(String shop_name, String addr, String tel) {
        this.shop_name = shop_name;
        this.addr = addr;
        this.tel = tel;
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

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "LocationSearchItem{" + "shop_name='" + shop_name + '\'' +
                ", addr='" + addr + '\'' + ", tel='" + tel + '\'' + '}';
    }
}
