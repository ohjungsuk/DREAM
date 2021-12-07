package com.example.dream.Services;

import android.util.Log;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.SignUpActivityView;
import com.example.dream.Interfaces.SignUpRetrofitInterface;
import com.example.dream.Models.SignUpBody;
import com.example.dream.Models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    public SignUpService(final SignUpActivityView signUpActivityView){
        mSignUpActivityView = signUpActivityView;
    }

    public void postSignUp(String id, String pw, String name, String area, String bloodtype){
        final SignUpRetrofitInterface signUpRetrofitInterface = ApplicationClass.getRetrofit2().create(SignUpRetrofitInterface.class);
        Log.d("RETRO", id);
        Log.d("RETRO", pw);
        Log.d("RETRO", name);
        Log.d("RETRO", area);
        Log.d("RETRO", bloodtype);
        signUpRetrofitInterface.requestSignUp(id,pw,name,area,bloodtype).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();
                if (signUpResponse!=null){
                    if(response.isSuccessful()){
                        Log.d("RETRO", "success1");
                        mSignUpActivityView.validateSuccess();

                    }else{
                        Log.d("RETRO", "fail1");
                        mSignUpActivityView.validateFailure();
                    }
                }else {
                    Log.d("RETRO", "???");
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("RETRO", "fail2");
            }
        });
    }
}
