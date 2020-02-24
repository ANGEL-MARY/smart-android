package com.example.smarttrade.models;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("_id")
    String id;

    @SerializedName("item")
    Item item;


    @SerializedName("sell_method")
    String sellMethod;

    @SerializedName("price")
    String price;

    @SerializedName("packet_weight")
    String packetWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getPacketWeight() {
        return packetWeight;
    }

    public void setPacketWeight(String packetWeight) {
        this.packetWeight = packetWeight;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getSellMethod() {
        return sellMethod;
    }

    public void setSellMethod(String sellMethod) {
        this.sellMethod = sellMethod;
    }
}
