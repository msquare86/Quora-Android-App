package com.example.quorabayactivity.quorabay;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayUserFollowRequestRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.FollowRequest;
import com.example.quorabayactivity.quorabay.models.UserProfileData;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayUserFollowRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_user_follow_request);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() , Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("QuorabayUserId" , "31b6aa8d-a1f8-4845-a9f4-046f326064d7");

        //String userId = getIntent().getStringExtra("QuorabayUserId");
        // String userName = getIntent().getStringExtra("QuorabayUserName");
        String userName = sharedPreferences.getString("QuorabayUserName" , "quorabayUser");

        //String userId = getIntent().getStringExtra("QuorabayUserId");
        Log.d("MNOP", "onCreate: " + userId);
        FollowRequest followRequest  = new FollowRequest();
        RecyclerView recyclerView = findViewById(R.id.row_quorabay_userfollow_request);
        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        List<UserProfileData> userProfileDataList = new ArrayList<>();

        Call<List<UserProfileData>> userFollowRequestCall = iPostAPI.getFollowRequestByUserId(userId);
        userFollowRequestCall.enqueue(new Callback<List<UserProfileData>>() {
            @Override
            public void onResponse(Call<List<UserProfileData>> call, Response<List<UserProfileData>> response) {
                if (response.body() != null){
                    for (UserProfileData userProfileData : response.body()){
                        Log.d("user", "onResponse: " + userProfileData.toString());
                        userProfileDataList.add(userProfileData);
                    }
                    Log.d("size", "onResponse: " + userProfileDataList.size());
                    QuorabayUserFollowRequestRecylerViewAdapter recyclerViewAdapter = new QuorabayUserFollowRequestRecylerViewAdapter(userProfileDataList, QuorabayUserFollowRequestActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayUserFollowRequestActivity.this));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<UserProfileData>> call, Throwable t) {
                Log.e("XYZ", "onFailure: " + t );
            }
        });
    }
}