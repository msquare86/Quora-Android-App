package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.QuorabayCommentsActivity;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.dto.PostReaction;
import com.example.quorabayactivity.quorabay.dto.ResponseMessage;
import com.example.quorabayactivity.quorabay.models.Answers;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayAnswersRecylerViewAdapter extends RecyclerView.Adapter<QuorabayAnswersRecylerViewAdapter.ViewHolder> {

    private final List<Answers> mAnswersList;
    private final Context mContext;
    private final String questionId;
    private final String questionText;
    private String userName = "quorabayUser";
    private int like = 0 , dislike = 0;

    public QuorabayAnswersRecylerViewAdapter(List<Answers> mAnswersList, Context mContext, String questionId, String questionText) {
        this.mAnswersList = mAnswersList;
        this.mContext = mContext;
        this.questionId = questionId;
        this.questionText = questionText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_answerlayout,parent,false);
        return new QuorabayAnswersRecylerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //final int[] like = {0};
        //final int[] dislike = {0};
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        Retrofit retrofit1 = RetrofitFollower.getInstance();
        IPostAPI iPostAPI1 = retrofit1.create(IPostAPI.class);


        Answers answers = mAnswersList.get(position);


        // TODO: 27/01/21 GetUsername
        Call<ResponseMessage> getUserNameApiCall = iPostAPI1.getUserNameByUserId(answers.getUserId());
        getUserNameApiCall.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.body() != null) {
                    Log.d("ABC", "onResponse: " + response.body().getMessage());
                    userName = response.body().getMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e("fail", "onFailure: " + t ) ;
            }
        });
        Log.d("username", "onBindViewHolder: " + userName);
        holder.tv_quorabay_answer_answerText.setText(answers.getAnswerText());
        holder.tv_quorabay_answer_answerBy.setText(userName);
        holder.tv_quorabay_answer_date.setText(answers.getDate().substring(0,10));
        holder.tv_quorabay_answer_questionText.setText(questionText);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostReaction postReaction = new PostReaction();
                postReaction.setAnswerId(answers.getAnswerId());
                postReaction.setReactionStatus(1);
                postReaction.setUserId(answers.getUserId());

                Call<ResponseBody> responseBodyCall = iPostAPI.reactOnLikeDislike(postReaction);
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext , "Like", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "Failed" + t, Toast.LENGTH_LONG).show();
                        Log.d("LikeFailure" , "T");
                    }
                });
            }
        });
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostReaction postReaction = new PostReaction();
                postReaction.setAnswerId(answers.getAnswerId());
                postReaction.setReactionStatus(-1);
                postReaction.setUserId(answers.getUserId());
                Call<ResponseBody> responseBodyCall = iPostAPI.reactOnLikeDislike(postReaction);
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(mContext , "Dislike", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, "Failed" + t, Toast.LENGTH_LONG).show();
                        Log.d("DislikeFailure" , "T");
                    }
                });
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCommentPage = new Intent(mContext , QuorabayCommentsActivity.class);
                gotoCommentPage.putExtra("AnswerId" , answers.getAnswerId());
                gotoCommentPage.putExtra("AnswerText" , answers.getAnswerText());
                mContext.startActivity(gotoCommentPage);
            }
        });

        holder.tv_quorabay_answer_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCommentPage = new Intent(mContext , QuorabayCommentsActivity.class);
                gotoCommentPage.putExtra("AnswerId" , answers.getAnswerId());
                gotoCommentPage.putExtra("AnswerText" , answers.getAnswerText());
                mContext.startActivity(gotoCommentPage);
            }
        });

        Call<Integer> likeCall = iPostAPI.countLikeByAnswerId(answers.getAnswerId());
        likeCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null) {
                    like = response.body().intValue();
                    Log.d("Like Cnt", "onResponse: " + like);
                    holder.tv_quorabay_answer_likes.setText(String.valueOf(like));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("fail like ", "onFailure: " + t );
            }
        });

        Call<Integer> dislikeCall = iPostAPI.countDislikeByAnswerId(answers.getAnswerId());
        dislikeCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() != null) {
                    dislike = response.body().intValue();
                    Log.d("Dislike Cnt", "onResponse: " + response.body().intValue());
                    holder.tv_quorabay_answer_dislikes.setText(String.valueOf(dislike));
                }
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("fail dislike", "onFailure: " + t );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAnswersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_quorabay_answer_answerBy;
        private TextView tv_quorabay_answer_date;
        private TextView tv_quorabay_answer_questionText;
        private TextView tv_quorabay_answer_answerText;
        private ImageView like , dislike , comment;
        private TextView tv_quorabay_answer_likes;
        private TextView tv_quorabay_answer_dislikes;
        private TextView tv_quorabay_answer_comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_quorabay_answer_answerBy = itemView.findViewById(R.id.tv_quorabay_answer_answerBy);
            tv_quorabay_answer_date = itemView.findViewById(R.id.tv_quorabay_answer_date);
            tv_quorabay_answer_questionText = itemView.findViewById(R.id.tv_quorabay_answer_questionText);
            tv_quorabay_answer_answerText = itemView.findViewById(R.id.tv_quorabay_answer_answerText);
            like = itemView.findViewById(R.id.iv_quorabay_answer_like);
            dislike = itemView.findViewById(R.id.iv_quorabay_answer_dislike);
            comment = itemView.findViewById(R.id.iv_quorabay_answer_comment);
            tv_quorabay_answer_likes = itemView.findViewById(R.id.tv_quorabay_answer_likes);
            tv_quorabay_answer_dislikes = itemView.findViewById(R.id.tv_quorabay_answer_dislikes);
            tv_quorabay_answer_comment = itemView.findViewById(R.id.tv_quorabay_answer_comment);
        }
    }

}
