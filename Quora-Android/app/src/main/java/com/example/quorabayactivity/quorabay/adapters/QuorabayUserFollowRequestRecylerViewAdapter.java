package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.QuorabayHomeActivity;
import com.example.quorabayactivity.quorabay.QuorabayUserProfileActivity;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.FollowRequest;
import com.example.quorabayactivity.quorabay.models.UserProfileData;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayUserFollowRequestRecylerViewAdapter extends RecyclerView.Adapter<QuorabayUserFollowRequestRecylerViewAdapter.ViewHolder> {

    private final List<UserProfileData> mUserFollowerRequestList;
    private final Context mContext;

    public QuorabayUserFollowRequestRecylerViewAdapter(List<UserProfileData> mUserFollowerList, Context mContext) {
        this.mUserFollowerRequestList = mUserFollowerList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_request_accept_decline,parent,false);
        return new QuorabayUserFollowRequestRecylerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        UserProfileData userSearch = mUserFollowerRequestList.get(position);
        holder.tv_quorabay_request_accept_decline_userName.setText(userSearch.getUserName());
        Glide.with(holder.iv_quorabay_request_accept_decline_image.getContext())
                .load(userSearch.getImageUrl())
                .placeholder(R.drawable.quorabay_profile_image)
                .into(holder.iv_quorabay_request_accept_decline_image);

        holder.btn_quorabay_request_accept_decline_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FollowRequest followRequest = new FollowRequest();
                followRequest.setUserId(userSearch.getUserId());
                followRequest.setFollowerId(userSearch.getFollowerId());

                Call<FollowRequest> followRequestCall = iPostAPI.approveFollowRequest(followRequest);
                followRequestCall.enqueue(new Callback<FollowRequest>() {
                    @Override
                    public void onResponse(Call<FollowRequest> call, Response<FollowRequest> response) {
                        Log.d("approve", "onResponse: " + response.body());
                    }

                    @Override
                    public void onFailure(Call<FollowRequest> call, Throwable t) {
                        Log.e("fail approve", "onFailure: " + t );
                    }
                });
            }
        });

        holder.btn_quorabay_request_accept_decline_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoHomePage = new Intent(mContext , QuorabayHomeActivity.class);
                mContext.startActivity(gotoHomePage);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuorabayUserProfileActivity.class);
                Gson gson = new Gson();
                String usersearch = gson.toJson(userSearch);
                intent.putExtra("UserSearch",  usersearch);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserFollowerRequestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_quorabay_request_accept_decline_image;
        private TextView tv_quorabay_request_accept_decline_userName;
        private Button btn_quorabay_request_accept_decline_accept;
        private Button btn_quorabay_request_accept_decline_decline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_quorabay_request_accept_decline_image = itemView.findViewById(R.id.iv_quorabay_request_accept_decline_image);
            tv_quorabay_request_accept_decline_userName = itemView.findViewById(R.id.tv_quorabay_request_accept_decline_userName);
            btn_quorabay_request_accept_decline_accept = itemView.findViewById(R.id.btn_quorabay_request_accept_decline_accept);
            btn_quorabay_request_accept_decline_decline = itemView.findViewById(R.id.btn_quorabay_request_accept_decline_decline);
        }
    }
}
