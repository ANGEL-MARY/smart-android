package com.example.smarttrade.retrofit;

import com.example.smarttrade.models.Auth;
import com.example.smarttrade.models.Buyer;
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.models.ResponseResult;
import com.example.smarttrade.models.Seller;

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


    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("user/verify")
    Call<Auth> verifyUser(@FieldMap HashMap<String, String> params);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("seller/")
    Call<ResponseResult<Seller>> sellerRegistration(@FieldMap HashMap<String, String> params);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("users/login")
    Call<ResponseResult<Buyer>> buyerRegistration(@FieldMap HashMap<String, String> params);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("users/login")
    Call<ResponseResult<Cart>> cartRegistration(@FieldMap HashMap<String, String> params);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("users/login")
    Call<ResponseResult<Product>> productRegistration(@FieldMap HashMap<String, String> params);




}
