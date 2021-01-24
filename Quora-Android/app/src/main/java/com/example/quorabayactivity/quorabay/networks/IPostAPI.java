package com.example.quorabayactivity.quorabay.networks;

import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.models.UserSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPostAPI {

    // List of All APIS

    // For Question
        @GET("question/findall")
        Call<List<Questions>> getQuestions();



    // For Answer


    // For Serach


    @GET("search/custom/{text}")
    Call<List<UserSearch>> getAll(@Path("text") String text);

    // For Ranking


    // For Emoji



}
