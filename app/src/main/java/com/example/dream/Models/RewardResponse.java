package com.example.dream.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RewardResponse {
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("data")
    @Expose
    private UserInfoResponse data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UserInfoResponse getData() {
        return data;
    }

    public void setData(UserInfoResponse data) {
        this.data = data;
    }
}
