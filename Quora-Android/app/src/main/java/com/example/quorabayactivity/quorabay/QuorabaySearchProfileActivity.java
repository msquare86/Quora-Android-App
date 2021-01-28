package com.example.quorabayactivity.quorabay;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.FollowRequest;
import com.example.quorabayactivity.quorabay.models.UserSearch;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabaySearchProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView userName;
    Button follow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_search_profile);

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        String user = (String) getIntent().getSerializableExtra("UserSearch");
        Gson gson = new Gson();

        //String UserId = getIntent().getStringExtra("QuorabayUserId");
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String UserId = sharedPreferences.getString("QuorabayUserId" , "31b6aa8d-a1f8-4845-a9f4-046f326064d7");

        UserSearch userSearch = gson.fromJson(user, UserSearch.class);
        profileImage = findViewById(R.id.quorabay_userSearch_profileImage);
        Glide.with(this)
                .load(userSearch.getImageUrl())
                .placeholder(R.drawable.quorabay_profile_image)
                .into(profileImage);


        userName = findViewById(R.id.quorabay_userSearch_userName);
        userName.setText(userSearch.getUserName());

        FollowRequest followRequest = new FollowRequest();
        followRequest.setUserId(userSearch.getUserId());
        followRequest.setFollowerId(UserId);
        Log.d("OPS", "onCreate: " + userSearch.getUserId() + UserId);
        follow = findViewById(R.id.btn_quorabay_user_search_follow);

        Call<Boolean> followRequestCall = iPostAPI.checkFollowing(followRequest);
        followRequestCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body() != null){
                    if (response.body().booleanValue()){
                        follow.setEnabled(false);
                    }else{
                        follow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Call<FollowRequest> followRequestCall = iPostAPI.addFollower(followRequest);
                                followRequestCall.enqueue(new Callback<FollowRequest>() {
                                    @Override
                                    public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                                        Toast.makeText(QuorabaySearchProfileActivity.this, "Follow Request", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<FollowRequest> call, Throwable t) {
                                        Log.d("BOOLEAN", "onFailure: " + t);
                                    }
                                });
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("fail", "onFailure: " + t );
            }
        });
    }
}