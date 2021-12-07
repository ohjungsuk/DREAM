package com.example.dream.Interfaces;

import com.example.dream.Models.DeleteResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Query;

public interface DeleteRequestListRetrofitInterface {
    @DELETE("/request")
    Call<DeleteResponse> deleteRL(@Query("request_id") String request_id);
}
