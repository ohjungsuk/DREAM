package com.example.dream;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationClass extends Application {
    // 서버 주소
    public static String BASE_URL = "http://10.0.2.2:8080/";

    public static SharedPreferences sSharedPreferences = null;

    // SharedPreferences 키 값
    public static String TAG = "TEMPLATE_APP";

    public static final Long X_ACCESS_TOKEN = null;

    public static String u_id = null;
    public static String donor_count = null;
    public static String b_type = null;
    public static String mode = null;
    public static String selection = null; // 1: 전체 조회 , 2: 혈액형별 조회

    //날짜 형식
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    // Retrofit 인스턴스
    public static Retrofit retrofit;


    @Override
    public void onCreate() {
        super.onCreate();
        if (sSharedPreferences == null) {
            sSharedPreferences = getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }
    }

    //    public static Retrofit getRetrofit() {
//        if (retrofit == null) {
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .readTimeout(5000, TimeUnit.MILLISECONDS)
//                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
//                    .addNetworkInterceptor(new XAccessTokenInterCeptor()) // JWT 자동 헤더 전송
//                    .build();
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return retrofit;
//    }
    public static Retrofit getRetrofit2() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
