package com.example.dream.Interfaces;

import com.example.dream.Models.UpdateResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpdateDonationCountRetrofitInterface {
    @POST("/reward")
    Call<UpdateResponse> updateDC(@Query("u_id") String u_id, @Query("serial_of_blood_donation") String serial_of_blood_donation,
                                  @Query("date") String date);
}
