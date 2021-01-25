package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
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

public class QuorabayUserFollowRequestRecylerViewAdapter extends RecyclerView.Adapter<QuorabayUserFollowRequestRecylerViewAdapter.ViewHolder> {

    private final List<UserSearch> mUserFollowerRequestList;
    private final Context mContext;

    public QuorabayUserFollowRequestRecylerViewAdapter(List<UserSearch> mUserFollowerList, Context mContext) {
        this.mUserFollowerRequestList = mUserFollowerList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_request_accept_decline,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserSearch userSearch = mUserFollowerRequestList.get(position);
        holder.tv_quorabay_request_accept_decline_userName.setText(userSearch.getUserName());
        Glide.with(holder.iv_quorabay_request_accept_decline_image.getContext())
                .load(userSearch.getImageUrl())
                .placeholder(R.drawable.quorabay_profile_image)
                .into(holder.iv_quorabay_request_accept_decline_image);

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
