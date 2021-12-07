package com.example.dream.Services;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.UpdateDonationCountActivityView;
import com.example.dream.Interfaces.UpdateDonationCountRetrofitInterface;
import com.example.dream.Models.UpdateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDCountService {
    private final UpdateDonationCountActivityView mUpdateDonationCountActivityView;

    public UpdateDCountService(final  UpdateDonationCountActivityView updateDonationCountActivityView){
        mUpdateDonationCountActivityView = updateDonationCountActivityView;
    }

    public void updateDonationCount(String u_id, String serial, String date){
        final UpdateDonationCountRetrofitInterface updateDonationCountRetrofitInterface = ApplicationClass.getRetrofit2().create(UpdateDonationCountRetrofitInterface.class);
        updateDonationCountRetrofitInterface.updateDC(u_id,serial,date).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                UpdateResponse updateResponse = response.body();
                if (updateResponse!=null){
                    if (response.isSuccessful()){
                        System.out.println("급혈횟수관리 통신 성공");
                        mUpdateDonationCountActivityView.updateDCSuccess();
                    }else {
                        System.out.println("급혈횟수관리 통신 실패");
                        mUpdateDonationCountActivityView.updateDCFailure();
                    }
                }else {
                    System.out.println("???");
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                System.out.println("아예 실패");
            }
        });
    }
}
