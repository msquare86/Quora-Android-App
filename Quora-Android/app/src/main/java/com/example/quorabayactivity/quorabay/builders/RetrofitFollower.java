package com.example.quorabayactivity.quorabay.builders;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFollower {
    private static Retrofit instance;

    private RetrofitFollower(){
    }

    public static Retrofit getInstance(){
        if(instance == null) {
            synchronized (RetrofitBuilder.class){
                if(instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl("http://10.177.1.213:9000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient())
                            .build();
                }
            }
        }
        return instance;
    }
}
