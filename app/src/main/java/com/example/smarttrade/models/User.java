package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    String id;

    @SerializedName("name")
    String name;


    @SerializedName("phone")
    String phone;


    @SerializedName("is_verified")
    boolean isVerified;


    @SerializedName("type")
    String type;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) { this.phone = phone;}


    public  boolean getVerified(){return  isVerified;}
    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


