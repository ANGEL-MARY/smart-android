package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Cart {


    @SerializedName("_id")
    String id;


    @SerializedName("product")
    Product product;

    @SerializedName("current_price")
    String current_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public String getCurrent_price() { return current_price; }

    public void setCurrent_price(String current_price) { this.current_price= current_price; }


}
