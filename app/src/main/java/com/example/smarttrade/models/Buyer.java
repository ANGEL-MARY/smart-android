package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Buyer {

    @SerializedName("_id")
    String id;

    @SerializedName("shopname")
    String shopname;

    @SerializedName("storetype")
    String storetype;

    @SerializedName("address")
    String address;

    @SerializedName("latitude")
    Double latitude;

    @SerializedName("longitude")
    Double longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopname() { return shopname; }

    public void setShopname(String shopname) { this.shopname = shopname; }

    public String getStoretype() { return storetype; }

    public void setStoretype(String storetype) { this.storetype = storetype; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}


