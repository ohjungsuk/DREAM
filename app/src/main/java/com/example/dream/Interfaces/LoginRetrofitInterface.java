package com.example.dream.Interfaces;

import com.example.dream.Models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {
    @GET("/user")
    Call<LoginResponse> requestLogIn(@Query("id") String id,@Query("password") String password);
}
