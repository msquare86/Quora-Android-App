package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
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
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.UserDetails;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.List;

import retrofit2.Retrofit;

public class QuorabayModeratorListRecylerViewAdapter extends RecyclerView.Adapter<QuorabayModeratorListRecylerViewAdapter.ViewHolder>{

    private List<UserDetails> userModeratorList;
    private Context mContext;
    private  String ownerId;

    Retrofit retrofit = RetrofitFollower.getInstance();
    IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

    public QuorabayModeratorListRecylerViewAdapter(List<UserDetails> userModeratorList, Context mContext, String ownerId) {
        this.userModeratorList = userModeratorList;
        this.mContext = mContext;
        this.ownerId = ownerId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_moderatorlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserDetails userSearch = userModeratorList.get(position);
        Log.d("image", "onBindViewHolder: " + userSearch.getImageUrl());
        Log.d("image", "onBindViewHolder: " + userSearch.getUserName());
        holder.tv_quorabay_owner_moderator_userName.setText(userSearch.getUserName());
        Glide.with(holder.iv_quorabay_owner_moderator_userImage.getContext())
                .load(userSearch.getImageUrl())
                .placeholder(R.drawable.quorabay_profile_image)
                .into(holder.iv_quorabay_owner_moderator_userImage);
        Log.d("tag", "onBindViewHolder: "+ userSearch.getUserId() + ownerId);
//        holder.btn_quorabay_owner_moderator_remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ModeratorDetails moderatorDetails = new ModeratorDetails();
//                moderatorDetails.setModeratorId(userSearch.getUserId());
//                moderatorDetails.setOwnerId(ownerId);
//
//                Log.d("TAG", "onClick: "+ moderatorDetails.getModeratorId());
//
//                Call<ResponseBody> deleteCall = iPostAPI.delelteModerator(moderatorDetails);
//                deleteCall.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        Log.d("TAG", "onResponse: "+ response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.d("TAG", "onFailure: "+ t);
//                    }
//                });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userModeratorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_quorabay_owner_moderator_userImage;
        TextView tv_quorabay_owner_moderator_userName;
        Button btn_quorabay_owner_moderator_remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_quorabay_owner_moderator_userImage = itemView.findViewById(R.id.iv_quorabay_owner_moderator_userImage);
            tv_quorabay_owner_moderator_userName = itemView.findViewById(R.id.tv_quorabay_owner_moderator_userName);
            btn_quorabay_owner_moderator_remove= itemView.findViewById(R.id.btn_quorabay_owner_moderator_remove);
        }
    }

}
