package com.example.smarttrade.config;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.smarttrade.interfaces.RetrofitCallBack;
import com.example.smarttrade.models.Auth;
import com.example.smarttrade.models.Buyer;
import com.example.smarttrade.models.Cart;
import com.example.smarttrade.models.Product;
import com.example.smarttrade.models.ResponseResult;
import com.example.smarttrade.models.Seller;
import com.example.smarttrade.retrofit.AppAPIInterface;
import com.example.smarttrade.retrofit.AppClient;

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

    public void userLogin(HashMap<String, String> loginParms, final  RetrofitCallBack<String> retrofitCallBack){
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

    public void verifyUser(HashMap<String, String> otpparams, final RetrofitCallBack<String> retrofitCallBack) {
        Call<Auth> responseCall = appAPIInterface.verifyUser(otpparams);
        responseCall.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                if (response.isSuccessful()) {
                    retrofitCallBack.Success(response.body().getAccessToken());
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
                    if(response.body().getSuccess().equals("true")){
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

    }
    public void productRegistration(HashMap<String, String> productParams, final  RetrofitCallBack<Product> retrofitCallBack){
        Call<ResponseResult<Product>> responseCall = appAPIInterface.productRegistration(productParams);

    }
    public void cartRegistration(HashMap<String, String> cartParams, final  RetrofitCallBack<Cart> retrofitCallBack){
        Call<ResponseResult<Cart>> responseCall = appAPIInterface.cartRegistration(cartParams);

    }

}
