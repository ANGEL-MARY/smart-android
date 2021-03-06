package com.example.smarttrade.retrofit;

import com.example.smarttrade.models.Auth;
import com.example.smarttrade.models.Buyer;
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.models.DeleteModel;
import com.example.smarttrade.models.Order;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.models.ResponseResult;
import com.example.smarttrade.models.Seller;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppAPIInterface {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("users/login")
    Call<Auth> userLogin(@FieldMap HashMap<String, String> params);


    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("users/verify")
    Call<Auth> verifyUser(@FieldMap HashMap<String, String> params);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("seller/")
    Call<ResponseResult<Seller>> sellerRegistration(@FieldMap HashMap<String, String> params);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("buyer/")
    Call<ResponseResult<Buyer>> buyerRegistration(@FieldMap HashMap<String, String> params);

    // Products

    @GET("products/all/")
    Call<ResponseResult<ArrayList<Product>>> getProducts();

    @GET("products/single/{productId}")
    Call<ResponseResult<Product>> getProduct(@Path("productId") String productId);

    @DELETE("products/{productId}")
    Call<ResponseResult<Product>> removeProduct(@Path("productId") String productId);


    //Carts

    @GET("cart/")
    Call<ResponseResult<ArrayList<Cart>>> getCarts();

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("cart/")
    Call<ResponseResult<Cart>> addCart(@FieldMap HashMap<String, String> cartParams);

    @DELETE("cart/{cartId}")
    Call<ResponseResult<DeleteModel>> removeCart(@Path("cartId") String cartId);

    // Orders

    @GET("orders/")
    Call<ResponseResult<ArrayList<Order>>> getOrders();

    @GET("orders/seller")
    Call<ResponseResult<ArrayList<Order>>> getSellerOrders();

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("orders/")
    Call<ResponseResult<String>> addToOrders(@FieldMap HashMap<String, String> params);

    @Headers({"Accept: application/json"})
    @FormUrlEncoded()
    @POST("orders/buy_now")
    Call<ResponseResult<Order>> buyNow(@FieldMap HashMap<String, String> params);

    @GET("orders/{orderId}")
    Call<ResponseResult<Order>> getOrder(@Path("orderId") String orderId);

    @PATCH("orders/{orderId}")
    Call<ResponseResult<Order>> updateOrder(@Path("orderId") String orderId);

    @DELETE("orders/{orderId}")
    Call<ResponseResult<DeleteModel>> removeOrder(@Path("orderId") String orderId);
}
