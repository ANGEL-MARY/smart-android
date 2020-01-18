package com.example.smarttrade.interfaces;

public interface RetrofitCallBack<T> {
    public void Success(T status);

    public void Failure(String error);
}
