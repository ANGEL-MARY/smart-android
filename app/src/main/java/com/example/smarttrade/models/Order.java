package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("_id")
    String id;

    @SerializedName("user")
    User user;

    @SerializedName("product")
    Product product;

    @SerializedName("current_price")
    String current_price;

    @SerializedName("status")
    String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public String getCurrent_price() { return current_price; }

    public void setCurrent_price(String current_price) { this.current_price= current_price; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
