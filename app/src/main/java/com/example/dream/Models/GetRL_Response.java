package com.example.dream.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetRL_Response {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("data")
    @Expose
    private List<RequestListInfo> data= null;

//    public GetRL_Response(Integer count, List<RequestListInfo> data) {
//        this.count = count;
//        this.data = data;
//    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<RequestListInfo> getData() {
        return data;
    }

    public void setData(List<RequestListInfo> data) {
        this.data = data;
    }
}
