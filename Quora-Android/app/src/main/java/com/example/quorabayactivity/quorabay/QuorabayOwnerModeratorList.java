package com.example.quorabayactivity.quorabay;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayModeratorListRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.UserDetails;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayOwnerModeratorList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_owner_moderator_list);

        List<UserDetails> userDetailsList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.row_quorabay_owner_moderator_list_recycler_view);

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        // TODO code now
        String ownerId = "u6";
        Call<List<UserDetails>> userDetailsCall = iPostAPI.findModeratorListByownerId(ownerId);
        userDetailsCall.enqueue(new Callback<List<UserDetails>>() {
            @Override
            public void onResponse(Call<List<UserDetails>> call, Response<List<UserDetails>> response) {
                if (response.body() != null){
                    for (UserDetails userDetails : response.body()){
                        userDetailsList.add(userDetails);
                    }
                    Log.d("moderator", "onResponse: " + userDetailsList.size());
                    QuorabayModeratorListRecylerViewAdapter recyclerViewAdapter = new QuorabayModeratorListRecylerViewAdapter(userDetailsList, QuorabayOwnerModeratorList.this , ownerId);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayOwnerModeratorList.this));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<UserDetails>> call, Throwable t) {

            }
        });
    }
}