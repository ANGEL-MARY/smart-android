package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    String name;

    @SerializedName("price")
    String price;

    public Product(String price, String name){
        this.name = name;
        this.price = price;
    }

    public void setName(String name){
        this.name =  name;
    }
    public String getName() {
        return name;
    }

    public void setPrice(String price){
        this.price =  price;
    }

    public  String getPrice(){
        return  price;
    }

}
