package com.example.getcryptowithretrofit;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrentAveragePrice {

    @SerializedName("mins")
    @Expose
    private int averageTime;

    @SerializedName("price")
    @Expose
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    }

}
