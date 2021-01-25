package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.models.UserSearch;

import java.util.List;

public class QuorabayUserFollowersRecylerViewAdapter extends RecyclerView.Adapter<QuorabayUserFollowersRecylerViewAdapter.ViewHolder> {

    private final List<UserSearch> mUserFollowerList;
    private final Context mContext;

    public QuorabayUserFollowersRecylerViewAdapter(List<UserSearch> mUserFollowerList, Context mContext) {
        this.mUserFollowerList = mUserFollowerList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_followeruser,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserSearch userSearch = mUserFollowerList.get(position);
        holder.tv_quorabay_search_userName.setText(userSearch.getUserName());
        Glide.with(holder.iv_quorabay_search_userImage.getContext())
                .load(userSearch.getImageUrl())
                .placeholder(R.drawable.quorabay_profile_image)
                .into(holder.iv_quorabay_search_userImage);

    }

    @Override
    public int getItemCount() {
        return mUserFollowerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_quorabay_search_userImage;
        private TextView tv_quorabay_search_userName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_quorabay_search_userImage = itemView.findViewById(R.id.iv_quorabay_search_userImage);
            tv_quorabay_search_userName = itemView.findViewById(R.id.tv_quorabay_search_userName);
        }
    }
}
