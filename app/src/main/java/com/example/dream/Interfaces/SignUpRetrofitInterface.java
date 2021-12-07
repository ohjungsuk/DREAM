package com.example.dream.Interfaces;

import com.example.dream.Models.SignUpBody;
import com.example.dream.Models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignUpRetrofitInterface {
    @POST("/user")
    Call<SignUpResponse> requestSignUp(@Query("id") String id,@Query("password") String pw,
                                       @Query("name") String name,@Query("area") String area,@Query("blood_type") String blood_type);
}
