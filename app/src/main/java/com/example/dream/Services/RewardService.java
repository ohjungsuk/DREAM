package com.example.dream.Services;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.RewardActivityView;
import com.example.dream.Interfaces.RewardRetrofitInterface;
import com.example.dream.Models.RewardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardService {
    private final RewardActivityView mRewardActivityView;

    public RewardService(final RewardActivityView rewardActivityView){
        mRewardActivityView = rewardActivityView;
    }

    public void getRewardS(String id,String address,String product, Integer need){
        final RewardRetrofitInterface rewardRetrofitInterface = ApplicationClass.getRetrofit2().create(RewardRetrofitInterface.class);
        rewardRetrofitInterface.getReward(id, address, product, need).enqueue(new Callback<RewardResponse>() {
            @Override
            public void onResponse(Call<RewardResponse> call, Response<RewardResponse> response) {
                final RewardResponse rewardResponse = response.body();
                if(rewardResponse == null){
                    System.out.println("보상 리스폰스 널");
                    mRewardActivityView.validateFailure();
                }else {
                    System.out.println("보상 통신 성공");
                    mRewardActivityView.validateSuccess(rewardResponse);
                }
            }

            @Override
            public void onFailure(Call<RewardResponse> call, Throwable t) {
                System.out.println("보상 통신 실패");
            }
        });
    }
}
