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
import com.example.quorabayactivity.quorabay.dto.CommentData;

import java.util.List;

public class QuorabayCommentsRecylerViewAdapter extends RecyclerView.Adapter<QuorabayCommentsRecylerViewAdapter.ViewHolder> {

    private final List<CommentData> mCommentsList;
    private final Context mContext;
    private final String answerId;
    private final String answerText;

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
        Log.d("adapter", "onBindViewHolder: "+ mCommentsList.size());
        Log.d("adapter", "onBindViewHolder: "+ comments.getAnswerId());
        //holder.tv_quorabay_comment_date.setText();
        holder.tv_quorabay_commentor_comment.setText(comments.getCommentText());
        holder.tv_quorabay_commentor_username.setText(comments.getUserId());
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
