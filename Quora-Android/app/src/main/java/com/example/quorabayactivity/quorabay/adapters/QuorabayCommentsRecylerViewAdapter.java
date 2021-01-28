package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.dto.CommentData;
import com.example.quorabayactivity.quorabay.dto.ResponseMessage;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayCommentsRecylerViewAdapter extends RecyclerView.Adapter<QuorabayCommentsRecylerViewAdapter.ViewHolder> {

    private final List<CommentData> mCommentsList;
    private final Context mContext;
    private final String answerId;
    private final String answerText;
    private String userName = "quorabayUser";
    public QuorabayCommentsRecylerViewAdapter(List<CommentData> mCommentsList, Context mContext, String answerId, String answerText) {
        this.mCommentsList = mCommentsList;
        this.mContext = mContext;
        this.answerId = answerId;
        this.answerText = answerText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_comments,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentData comments = mCommentsList.get(position);

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        Call<ResponseMessage> getUserNameApiCall = iPostAPI.getUserNameByUserId(comments.getUserId());
        getUserNameApiCall.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.body() != null) {
                    Log.d("XYZ", "onResponse: " + response.body().getMessage());
                    userName = response.body().getMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e("fail", "onFailure: " + t ) ;
            }
        });
        //java.util.Date date = new java.util.Date();
        //System.out.println(date);
        Log.d("username", "onBindViewHolder: " + userName);
        holder.tv_quorabay_comment_date.setText("2021-01-28");
        holder.tv_quorabay_commentor_comment.setText(comments.getCommentText());
        holder.tv_quorabay_commentor_username.setText(userName);
    }

    @Override
    public int getItemCount() {
        return mCommentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

         TextView tv_quorabay_commentor_username;
         TextView tv_quorabay_commentor_comment;
         TextView tv_quorabay_comment_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_quorabay_commentor_username = itemView.findViewById(R.id.tv_quorabay_commentor_username);
            tv_quorabay_commentor_comment = itemView.findViewById(R.id.tv_quorabay_commentor_comment);
            tv_quorabay_comment_date = itemView.findViewById(R.id.tv_quorabay_comment_date);
        }
    }
}
