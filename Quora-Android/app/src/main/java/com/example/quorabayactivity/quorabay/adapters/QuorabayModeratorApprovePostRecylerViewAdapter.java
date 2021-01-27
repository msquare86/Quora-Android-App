package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.QuestionRequest;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayModeratorApprovePostRecylerViewAdapter extends RecyclerView.Adapter<QuorabayModeratorApprovePostRecylerViewAdapter.ViewHolder>{

    private List<QuestionRequest> mQuestionList;
    private Context mContext;

    public QuorabayModeratorApprovePostRecylerViewAdapter(List<QuestionRequest> mQuestionList, Context mContext) {
        this.mQuestionList = mQuestionList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_moderator_approved_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionRequest questionRequest = mQuestionList.get(position);
        holder.tv_quorabay_moderator_approvePost_username.setText(questionRequest.getUserId());
        holder.tv_quorabay_moderator_approvePost_business_page.setText(questionRequest.getCorporateId());
        holder.tv_quorabay_moderator_approvePost_question.setText(questionRequest.getQuestionText());

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        holder.btn_quorabay_moderator_approvePost_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> responseDecline = iPostAPI.getApprovePostdecline(questionRequest.getRequestId());
                responseDecline.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("decline", "onResponse: " + response.body());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("decline", "onFailure: " + t);
                    }
                });
            }
        });

        holder.btn_quorabay_moderator_approvePost_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Questions> responseAccept = iPostAPI.getApprovePostAccept(questionRequest.getRequestId());
                responseAccept.enqueue(new Callback<Questions>() {
                    @Override
                    public void onResponse(Call<Questions> call, Response<Questions> response) {
                        if (response.body() != null){
                            Log.d("question", "onResponse: " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Questions> call, Throwable t) {
                        Log.e("question fail", "onFailure: " + t );
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_quorabay_moderator_approvePost_username;
        TextView tv_quorabay_moderator_approvePost_business_page;
        TextView tv_quorabay_moderator_approvePost_question;
        Button btn_quorabay_moderator_approvePost_accept;
        Button btn_quorabay_moderator_approvePost_decline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_quorabay_moderator_approvePost_username = itemView.findViewById(R.id.tv_quorabay_moderator_approvePost_username);
            tv_quorabay_moderator_approvePost_business_page = itemView.findViewById(R.id.tv_quorabay_moderator_approvePost_business_page);
            tv_quorabay_moderator_approvePost_question = itemView.findViewById(R.id.tv_quorabay_moderator_approvePost_question);
            btn_quorabay_moderator_approvePost_accept = itemView.findViewById(R.id.btn_quorabay_moderator_approvePost_accept);
            btn_quorabay_moderator_approvePost_decline = itemView.findViewById(R.id.btn_quorabay_moderator_approvePost_decline);
        }
    }

}
