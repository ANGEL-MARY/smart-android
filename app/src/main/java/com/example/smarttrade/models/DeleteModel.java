package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class DeleteModel {
    @SerializedName("deleted_id")
    String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
