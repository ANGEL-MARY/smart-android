package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("_id")
    String id;


    @SerializedName("name")
    String name;

    @SerializedName("image")
    String imageUrl;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
