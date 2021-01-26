package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.QuorabayModeratorApprovePostActivity;
import com.example.quorabayactivity.quorabay.models.CorporateDeatails;

import java.util.List;

public class QuorabayModeratorpageRecylerViewAdapter extends  RecyclerView.Adapter<QuorabayModeratorpageRecylerViewAdapter.ViewHolder> {

    private List<CorporateDeatails> mCorporateDetails;
    private Context mContext;

    public QuorabayModeratorpageRecylerViewAdapter(List<CorporateDeatails> mCorporateDetails, Context mContext) {
        this.mCorporateDetails = mCorporateDetails;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_corporate_pagelayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CorporateDeatails corporateDeatails = mCorporateDetails.get(position);
        holder.tv_quorabay_corporate_pagename.setText(corporateDeatails.getCorporateName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoApproveRequest = new Intent(mContext , QuorabayModeratorApprovePostActivity.class);
                gotoApproveRequest.putExtra("corporateId" , corporateDeatails.getCorporateId());
                mContext.startActivity(gotoApproveRequest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCorporateDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_quorabay_corporate_pagename;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_quorabay_corporate_pagename = itemView.findViewById(R.id.tv_quorabay_corporate_pagename);
        }
    }

}
