package com.example.smarttrade.retrofit;

import com.example.smarttrade.models.Auth;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AppAPIInterface {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("users/login")
    Call<Auth> userLogin(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded()
    @POST("user/verify")
    Call<Auth> verifyUser(@FieldMap HashMap<String, String> params);


}
