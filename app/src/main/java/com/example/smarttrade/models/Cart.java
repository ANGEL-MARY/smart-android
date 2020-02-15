package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("product")
    String product;

    @SerializedName("current_price")
    String current_price;


    public String getProduct() { return product; }

    public void setProduct(String product) { this.product = product; }

    public String getCurrent_price() { return current_price; }

    public void setCurrent_price(String current_price) { this.current_price= current_price; }


}
