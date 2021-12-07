package com.example.dream.Interfaces;

import com.example.dream.Models.SignUpResponse;
import com.example.dream.Models.WriteRL_Response;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WriteRL_RetrofitInterface {
    @POST("/request")
    Call<WriteRL_Response> writeRL(@Query("u_id") String u_id, @Query("date") String date,
                                         @Query("blood_type") String blood_type, @Query("blood_kind") String blood_kind,
                                   @Query("need") Integer need,@Query("deadline") String deadline,@Query("patient_name") String patient_name,
                                   @Query("patient_loc") String patient_loc,@Query("patient_id") String patient_id,@Query("hospital_name") String hospital_name,
                                   @Query("phone") String phone,@Query("patient_sex") String patient_sex,
                                   @Query("patient_age") Integer patient_age,@Query("patient_birth") String patient_birth,@Query("request_detail") String request_detail);
}
