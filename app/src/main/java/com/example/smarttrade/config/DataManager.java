package com.example.smarttrade.config;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Auth;
import com.example.smarttrade.models.Buyer;
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.models.DeleteModel;
import com.example.smarttrade.models.Order;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.models.ResponseResult;
import com.example.smarttrade.models.Seller;
import com.example.smarttrade.retrofit.AppAPIInterface;
import com.example.smarttrade.retrofit.AppClient;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {
    public static DataManager dataManager = null;
    private AppAPIInterface appAPIInterface;
    private Context mContext;

    public static DataManager getDataManager() {
        if (dataManager == null)
            dataManager = new DataManager();

        return dataManager;
    }

    public void init(Context context) {
        appAPIInterface = AppClient.getAPiClient().create(AppAPIInterface.class);
        mContext = context;
    }

//    public void userLogin(HashMap<String,String> loginParams, final RetrofitCallBack<String> retrofitCallBack){
//        Call<Auth> responseCall = AppClient.getAPiClient().create(AppAPIInterface.class)
//                .userLogin(loginParams);
//        responseCall.enqueue(new Callback<Auth>() {
//            @Override
//            public void onResponse(Call<Auth> call, Response<Auth> response) {
//                if (response.isSuccessful()){
//                    retrofitCallBack.Success(response.body().getAccessToken());
//
//                } else {
//                    retrofitCallBack.Failure("some error happens !");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Auth> call, Throwable t) {
//                retrofitCallBack.Failure("some error happens !");
//            }
//        });
//    }

    public void userLogin(HashMap<String, String> loginParms, final  RetrofitCallBack<Boolean> retrofitCallBack){
        Call<Auth> responseCall = AppClient.getAPiClient().create(AppAPIInterface.class).userLogin(loginParms);
        responseCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if(response.isSuccessful()){

                    retrofitCallBack.Success(response.body().getSuccess());
                }else{
                    retrofitCallBack.Failure("Something went wrokng");
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                retrofitCallBack.Failure("Something went wrokng");
            }
        });
    }

    public void verifyUser(HashMap<String, String> otpparams, final RetrofitCallBack<Auth> retrofitCallBack) {
        Call<Auth> responseCall = AppClient.getAPiClient().create(AppAPIInterface.class).verifyUser(otpparams);
        responseCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()) {
                    retrofitCallBack.Success(response.body());
                } else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                retrofitCallBack.Failure("Some error happened !!");
            }
        });

    }
    public void sellerRegistration(HashMap<String, String> sellerParams, final  RetrofitCallBack<Seller> retrofitCallBack){
        Call<ResponseResult<Seller>> responseCall =  appAPIInterface.sellerRegistration(sellerParams);
        responseCall.enqueue(new Callback<ResponseResult<Seller>>() {
            @Override
            public void onResponse(Call<ResponseResult<Seller>> call, Response<ResponseResult<Seller>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }

            }

            @Override
            public void onFailure(Call<ResponseResult<Seller>> call, Throwable t) {

                retrofitCallBack.Failure("Some error happened !!");
            }
        });


    }
    public void buyerRegistration(HashMap<String, String> buyerParams, final  RetrofitCallBack<Buyer> retrofitCallBack){
        Call<ResponseResult<Buyer>> responseCall = appAPIInterface.buyerRegistration(buyerParams);
        responseCall.enqueue(new Callback<ResponseResult<Buyer>>() {
            @Override
            public void onResponse(Call<ResponseResult<Buyer>> call, Response<ResponseResult<Buyer>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<Buyer>> call, Throwable t) {
                retrofitCallBack.Failure("Some error happened !!");
            }
        });
    }


    // Products

    public  void  getProducts(final RetrofitCallBack<ArrayList<Product>> retrofitCallBack){
        Call<ResponseResult<ArrayList<Product>>> responseCall = appAPIInterface.getProducts();
        responseCall.enqueue(new Callback<ResponseResult<ArrayList<Product>>>() {
            @Override
            public void onResponse(Call<ResponseResult<ArrayList<Product>>> call, Response<ResponseResult<ArrayList<Product>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<ArrayList<Product>>> call, Throwable t) {
                retrofitCallBack.Failure("Some error happened !!");
            }
        });
    }

    public void getProduct(String productId, final RetrofitCallBack<Product> retrofitCallBack){
        Call<ResponseResult<Product>> responseCall =  appAPIInterface.getProduct(productId);
        responseCall.enqueue(new Callback<ResponseResult<Product>>() {
            @Override
            public void onResponse(Call<ResponseResult<Product>> call, Response<ResponseResult<Product>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<Product>> call, Throwable t) {
                retrofitCallBack.Failure("Some error happened !!");
            }
        });
    }

    public void  removeProduct(String productId, final RetrofitCallBack<Product> retrofitCallBack){
        Call<ResponseResult<Product>> responseCall = appAPIInterface.removeProduct(productId);
        responseCall.enqueue(new Callback<ResponseResult<Product>>() {
            @Override
            public void onResponse(Call<ResponseResult<Product>> call, Response<ResponseResult<Product>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<Product>> call, Throwable t) {
                retrofitCallBack.Failure("Something went wrong");
            }
        });
    }

    ////////////
    //  Cart  //
    ////////////

    public void getCarts(final RetrofitCallBack<ArrayList<Cart>> retrofitCallBack){
        Call<ResponseResult<ArrayList<Cart>>> responseCall = appAPIInterface.getCarts();
        responseCall.enqueue(new Callback<ResponseResult<ArrayList<Cart>>>() {
            @Override
            public void onResponse(Call<ResponseResult<ArrayList<Cart>>> call, Response<ResponseResult<ArrayList<Cart>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<ArrayList<Cart>>> call, Throwable t) {
                retrofitCallBack.Failure("Some error happened !!");
            }
        });
    }

    public void addCart(HashMap<String, String> cartParams, final  RetrofitCallBack<Cart> retrofitCallBack){
        Call<ResponseResult<Cart>> responseCall = appAPIInterface.addCart(cartParams);
        responseCall.enqueue(new Callback<ResponseResult<Cart>>() {
            @Override
            public void onResponse(Call<ResponseResult<Cart>> call, Response<ResponseResult<Cart>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){

                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<Cart>> call, Throwable t) {

                retrofitCallBack.Failure("Some error happened !!");
            }
        });
    }

    public  void removeCart(String cartId, final  RetrofitCallBack<DeleteModel> retrofitCallBack){
        Call<ResponseResult<DeleteModel>> responseCall = appAPIInterface.removeCart(cartId);
        responseCall.enqueue(new Callback<ResponseResult<DeleteModel>>() {
            @Override
            public void onResponse(Call<ResponseResult<DeleteModel>> call, Response<ResponseResult<DeleteModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<DeleteModel>> call, Throwable t) {

            }
        });
    }

    ///////////////
    //  Orders  //
    /////////////

    public void addToOrders(HashMap<String, String> orderParams, final  RetrofitCallBack<String> retrofitCallBack){
        Call<ResponseResult<String>> responseCall =  appAPIInterface.addToOrders(orderParams);
        responseCall.enqueue(new Callback<ResponseResult<String>>() {
            @Override
            public void onResponse(Call<ResponseResult<String>> call, Response<ResponseResult<String>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<String>> call, Throwable t) {

            }
        });
    }

    public  void  buyNow(HashMap<String, String> orderParams, final  RetrofitCallBack<Order> retrofitCallBack){
        Call<ResponseResult<Order>> responseCall = appAPIInterface.buyNow(orderParams);
        responseCall.enqueue(new Callback<ResponseResult<Order>>() {
            @Override
            public void onResponse(Call<ResponseResult<Order>> call, Response<ResponseResult<Order>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<Order>> call, Throwable t) {

            }
        });

    }

    public void getOrders(final RetrofitCallBack<ArrayList<Order>> retrofitCallBack){
        Call<ResponseResult<ArrayList<Order>>> responseCall = appAPIInterface.getOrders();
        responseCall.enqueue(new Callback<ResponseResult<ArrayList<Order>>>() {
            @Override
            public void onResponse(Call<ResponseResult<ArrayList<Order>>> call, Response<ResponseResult<ArrayList<Order>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<ArrayList<Order>>> call, Throwable t) {

            }
        });
    }

    public void getSellerOrders(final RetrofitCallBack<ArrayList<Order>> retrofitCallBack){
        Call<ResponseResult<ArrayList<Order>>> responseCall = appAPIInterface.getSellerOrders();
        responseCall.enqueue(new Callback<ResponseResult<ArrayList<Order>>>() {
            @Override
            public void onResponse(Call<ResponseResult<ArrayList<Order>>> call, Response<ResponseResult<ArrayList<Order>>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<ArrayList<Order>>> call, Throwable t) {

            }
        });
    }




    public  void removeOrder(String orderId, final  RetrofitCallBack<DeleteModel> retrofitCallBack ){
        Call<ResponseResult<DeleteModel>> responseCall = appAPIInterface.removeOrder(orderId);
        responseCall.enqueue(new Callback<ResponseResult<DeleteModel>>() {
            @Override
            public void onResponse(Call<ResponseResult<DeleteModel>> call, Response<ResponseResult<DeleteModel>> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        retrofitCallBack.Success(response.body().getData());
                    }else{
                        retrofitCallBack.Failure("Something went wrong");
                    }
                }else {
                    retrofitCallBack.Failure("Some error happened !!");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult<DeleteModel>> call, Throwable t) {

            }
        });
    }
}

