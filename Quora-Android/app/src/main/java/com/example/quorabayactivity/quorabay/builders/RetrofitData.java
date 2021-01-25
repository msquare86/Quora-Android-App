package com.example.quorabayactivity.quorabay.builders;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitData {
    private static Retrofit instance;

    private RetrofitData(){

    }

    public static Retrofit getInstance(){
        if(instance == null) {
            synchronized (RetrofitBuilder.class){
                if(instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl("http://10.177.1.164:8080/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient())
                            .build();
                }
            }
        }
        return instance;
    }
}
