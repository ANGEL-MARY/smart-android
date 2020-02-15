package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Buyer {
    @SerializedName("shopname")
    String shopname;

    @SerializedName("storetype")
    String storetype;

    @SerializedName("address")
    String address;

    @SerializedName("latitude")
    double latitude;

    @SerializedName("longitude")
    double longitude;


    public String getShopname() { return shopname; }

    public void setShopname(String shopname) { this.shopname = shopname; }

    public String getStoretype() { return storetype; }

    public void setStoretype(String storetype) { this.storetype = storetype; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }


    public Double getlatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getlongitude (){ return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude;}
}


