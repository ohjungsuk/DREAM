package com.example.dream.Services;

import android.util.Log;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.WriteRL_RetrofitInterface;
import com.example.dream.Interfaces.WriteRequestListActivityView;
import com.example.dream.Models.WriteRL_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteRL_Service {
    private final WriteRequestListActivityView mWriteRequestListActivityView;

    public WriteRL_Service(final WriteRequestListActivityView writeRequestListActivityView){
        mWriteRequestListActivityView = writeRequestListActivityView;
    }

    public void postRL(String u_id,String date, String blood_type, String blood_kind,Integer need,String deadline,String patient_name,
                       String patient_loc,String patient_id,String hospital_name, String phone,String patient_sex,Integer patient_age,String patient_birth,String request_detail){
        final WriteRL_RetrofitInterface writeRL_retrofitInterface = ApplicationClass.getRetrofit2().create(WriteRL_RetrofitInterface.class);
        writeRL_retrofitInterface.writeRL(u_id, date, blood_type, blood_kind, need, deadline, patient_name, patient_loc, patient_id, hospital_name, phone, patient_sex, patient_age, patient_birth,request_detail).enqueue(new Callback<WriteRL_Response>() {
            @Override
            public void onResponse(Call<WriteRL_Response> call, Response<WriteRL_Response> response) {
                WriteRL_Response writeRL_response = response.body();
                if (writeRL_response!=null){
                    if(response.isSuccessful()){
                        Log.d("write", "success1");
                        mWriteRequestListActivityView.validateSuccess();

                    }else{
                        Log.d("write", "fail1");
                        mWriteRequestListActivityView.validateFailure();
                    }
                }else {
                    Log.d("write", "???");
                }
            }

            @Override
            public void onFailure(Call<WriteRL_Response> call, Throwable t) {
                Log.d("write", "XXfail");
            }
        });
    }
}
