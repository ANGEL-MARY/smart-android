package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("_id")
    String id;

    @SerializedName("range")
    String range;

    @SerializedName("address")
    String address;

    @SerializedName("latitude")
    Double latitude;

    @SerializedName("longitude")
    Double longitude;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getRange() { return range; }

    public void setRange(String range) { this.range = range; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address= address; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude= latitude; }

    public Double getLongitude (){ return longitude; }

    public void setLongitude(Double longitude) { this.longitude= longitude;}
}
