package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Auth {

    @SerializedName("token")
    String accessToken;

    @SerializedName("refresh_token")
    String refreshToken;


    @SerializedName("success")
    String success;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
