package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Seller {
    @SerializedName("range")
    String range;

    @SerializedName("address")
    String address;

    @SerializedName("latitude")
    double latitude;

    @SerializedName("longitude")
    double longitude;


    public String getRange() { return range; }

    public void setRange(String range) { this.range = range; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address= address; }

    public Double getlatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude= latitude; }

    public Double getlongitude (){ return longitude; }

    public void setLongitude(Double longitude) { this.longitude= longitude;}
}
