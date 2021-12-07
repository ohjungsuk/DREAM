package com.example.dream.Services;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.GetRL_ActivityView;
import com.example.dream.Interfaces.GetRL_RetrofitInterface;
import com.example.dream.Models.GetRL_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRL_Service {
    private final GetRL_ActivityView mGetRL_activityView;

    public GetRL_Service(GetRL_ActivityView getRL_activityView){
        this.mGetRL_activityView = getRL_activityView;
    }

    public void getRequestList(String u_id){
        final GetRL_RetrofitInterface getRL_retrofitInterface = ApplicationClass.getRetrofit2().create(GetRL_RetrofitInterface.class);
        System.out.println("!! "+ u_id);
        getRL_retrofitInterface.getAllRL(u_id).enqueue(new Callback<GetRL_Response>() {
            @Override
            public void onResponse(Call<GetRL_Response> call, Response<GetRL_Response> response) {
                final GetRL_Response getRL_response = response.body();
                if (getRL_response != null){
                    System.out.println("success");
                    mGetRL_activityView.getRLSuccess(getRL_response);

                }else {
                    System.out.println("success but fail");
                    mGetRL_activityView.getRLFailure();
                }
            }

            @Override
            public void onFailure(Call<GetRL_Response> call, Throwable t) {
                System.out.println("통신 실패");
            }
        });
    }
}
