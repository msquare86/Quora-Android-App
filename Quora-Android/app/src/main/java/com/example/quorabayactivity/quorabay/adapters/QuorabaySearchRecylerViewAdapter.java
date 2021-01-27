package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.QuorabaySearchProfileActivity;
import com.example.quorabayactivity.quorabay.models.UserSearch;
import com.google.gson.Gson;

import java.util.List;

public class QuorabaySearchRecylerViewAdapter extends RecyclerView.Adapter<QuorabaySearchRecylerViewAdapter.ViewHolder>{

    private List<UserSearch> userSearchList;
    private Context mContext;

    public QuorabaySearchRecylerViewAdapter(List<UserSearch> userSearchList, Context mContext) {
        this.userSearchList = userSearchList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_searchuser,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            UserSearch userSearch = userSearchList.get(position);
            Log.d("image", "onBindViewHolder: " + userSearch.getImageUrl());
            holder.tv_quorabay_search_userName.setText(userSearch.getUserName());
            Glide.with(holder.iv_quorabay_search_userImage.getContext())
                    .load(userSearch.getImageUrl())
                    .placeholder(R.drawable.quorabay_profile_image)
                    .into(holder.iv_quorabay_search_userImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Item Click" , "Hello" );
                    Intent intent = new Intent(mContext, QuorabaySearchProfileActivity.class);
                    Gson gson = new Gson();
                    String usersearch = gson.toJson(userSearch);
                    intent.putExtra("UserSearch",  usersearch);
                    mContext.startActivity(intent);
                }
            });
     }

    @Override
    public int getItemCount() {
        return userSearchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

         ImageView iv_quorabay_search_userImage;
         TextView tv_quorabay_search_userName;
         public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_quorabay_search_userImage = itemView.findViewById(R.id.iv_quorabay_search_userImage);
            tv_quorabay_search_userName = itemView.findViewById(R.id.tv_quorabay_search_userName);
         }
    }
}
