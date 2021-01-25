package com.example.quorabayactivity.quorabay;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayUserFollowersRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.UserDetails;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayFollowerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_follower_list);

        RecyclerView recyclerView = findViewById(R.id.quorabay_follower_userFollowers_RecyclerView);

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);
        List<UserDetails> userDetailsList = new ArrayList<>();
        Call<List<UserDetails>> userFollowerCall = iPostAPI.getFollowersByUserId("u4");
        userFollowerCall.enqueue(new Callback<List<UserDetails>>() {
            @Override
            public void onResponse(Call<List<UserDetails>> call, Response<List<UserDetails>> response) {
                if (response.body() != null) {
                    for (UserDetails userDetails : response.body()) {
                        Log.d("Response", "onResponse: sfsfs");
                        userDetailsList.add(userDetails);
                    }
                }
                Log.d("Question page", "onResponse: " + userDetailsList.size());
                QuorabayUserFollowersRecylerViewAdapter recyclerViewAdapter = new QuorabayUserFollowersRecylerViewAdapter(userDetailsList, QuorabayFollowerListActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayFollowerListActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<UserDetails>> call, Throwable t) {
                Log.e("Fail", "onFailure: "+t );
            }
        });
    }
}