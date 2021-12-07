package com.example.dream.Interfaces;

import com.example.dream.Models.RewardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RewardRetrofitInterface {
    @GET("/reward")
    Call<RewardResponse> getReward(@Query("u_id") String u_id,@Query("address") String address,
                                   @Query("product") String product,@Query("need") Integer need);
}
