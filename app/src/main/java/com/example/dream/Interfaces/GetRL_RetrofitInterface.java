package com.example.dream.Interfaces;

import com.example.dream.Models.GetRL_Response;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRL_RetrofitInterface {
    @GET("/request")
    Call<GetRL_Response> getAllRL(@Query("id") String id);
}
