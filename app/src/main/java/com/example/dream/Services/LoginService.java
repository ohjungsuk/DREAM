package com.example.dream.Services;

import android.util.Log;

import com.example.dream.ApplicationClass;
import com.example.dream.Interfaces.LoginActivityView;
import com.example.dream.Interfaces.LoginRetrofitInterface;
import com.example.dream.Models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private final LoginActivityView mloginActivityView;

    public LoginService(final LoginActivityView loginActivityView){
        mloginActivityView = loginActivityView;
    }

    public void postLogIn(String id,String password){
        final LoginRetrofitInterface loginRetrofitInterface = ApplicationClass.getRetrofit2().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.requestLogIn(id,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null){
                    mloginActivityView.validateFailure();
                }else {
                    mloginActivityView.validateSuccess(loginResponse);
                    Log.d("login", "logIN success");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("login", "login fail");
            }
        });
    }
}
