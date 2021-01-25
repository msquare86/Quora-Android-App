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
import com.example.quorabayactivity.quorabay.models.UserSearch;

import java.util.List;

public class QuorabaySearchModeratorRecylerViewAdapter extends RecyclerView.Adapter<QuorabaySearchModeratorRecylerViewAdapter.ViewHolder>{

    private List<UserSearch> userSearchList;
    private Context mContext;

    public QuorabaySearchModeratorRecylerViewAdapter(List<UserSearch> userSearchList, Context mContext) {
        this.userSearchList = userSearchList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_searchmoderator,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserSearch userSearch = userSearchList.get(position);
        Log.d("image", "onBindViewHolder: " + userSearch.getImageUrl());
        holder.tv_quorabay_searchmoderator_userName.setText(userSearch.getUserName());
        Glide.with(holder.iv_quorabay_searchmoderator_userImage.getContext())
                .load(userSearch.getImageUrl())
                .placeholder(R.drawable.quorabay_profile_image)
                .into(holder.iv_quorabay_searchmoderator_userImage);

        holder.btn_quorabay_search_moderator_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return userSearchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_quorabay_searchmoderator_userImage;
        TextView tv_quorabay_searchmoderator_userName;
        Button btn_quorabay_search_moderator_add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_quorabay_searchmoderator_userImage = itemView.findViewById(R.id.iv_quorabay_searchmoderator_userImage);
            tv_quorabay_searchmoderator_userName = itemView.findViewById(R.id.tv_quorabay_searchmoderator_userName);
            btn_quorabay_search_moderator_add = itemView.findViewById(R.id.btn_quorabay_search_moderator_add);
        }
    }
}
