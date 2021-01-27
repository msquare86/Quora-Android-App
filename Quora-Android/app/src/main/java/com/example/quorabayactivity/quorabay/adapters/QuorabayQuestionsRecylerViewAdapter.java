package com.example.quorabayactivity.quorabay.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.QuorabayAnswerActivity;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.dto.ResponseMessage;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayQuestionsRecylerViewAdapter extends RecyclerView.Adapter<QuorabayQuestionsRecylerViewAdapter.ViewHolder> {

    private final List<Questions> mQuestionsList;
    private final Context mContext;
    private String userName = "quorabayUser";
    public QuorabayQuestionsRecylerViewAdapter(List<Questions> mQuestionsList, Context mContext) {
        this.mQuestionsList = mQuestionsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quorabay_postlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Questions questions = mQuestionsList.get(position);
        String choice = "";
        if (questions.getCategory().getCategoryName() != null)
            choice = questions.getCategory().getCategoryName();

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        Log.d("question user Id", "onBindViewHolder: " + questions.getUserId());
        Call<ResponseMessage> getUserNameApiCall = iPostAPI.getUserNameByUserId(questions.getUserId());
        getUserNameApiCall.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                Log.d("TAG", "onResponse: "+ response.body().getMessage());
                if (response.body() != null) {
                    Log.d("MNO", "onResponse: " + response.body().getMessage());
                    userName = response.body().getMessage();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e("fail", "onFailure: " + t ) ;
            }
        });
        Log.d("username", "onBindViewHolder: " + userName);
        holder.tv_post_questionText.setText(questions.getQuestionText());
        holder.tv_quorabay_post_asked_on.setText(questions.getDate().substring(0,10));
        holder.tv_quorabay_post_asked.setText(userName);
        holder.tv_quorabay_post_topic.setText(choice);

        holder.answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAnswerPage = new Intent(mContext , QuorabayAnswerActivity.class);
                gotoAnswerPage.putExtra("QuestionId" , questions.getQuestionId());
                gotoAnswerPage.putExtra("QuestionText" , questions.getQuestionText());
                mContext.startActivity(gotoAnswerPage);
            }
        });

        holder.answers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAnswerPage = new Intent(mContext , QuorabayAnswerActivity.class);
                gotoAnswerPage.putExtra("QuestionId" , questions.getQuestionId());
                gotoAnswerPage.putExtra("QuestionText" , questions.getQuestionText());
                mContext.startActivity(gotoAnswerPage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_quorabay_post_asked;
        private TextView tv_quorabay_post_topic;
        private TextView tv_quorabay_post_asked_on;
        private TextView tv_post_questionText;
        private ImageView like, dislike, answer;
        private TextView likes, dislikes , answers;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_quorabay_post_asked = itemView.findViewById(R.id.tv_quorabay_post_asked);
            tv_quorabay_post_topic = itemView.findViewById(R.id.tv_quorabay_post_topic);
            tv_quorabay_post_asked_on = itemView.findViewById(R.id.tv_quorabay_post_asked_on);
            like = itemView.findViewById(R.id.iv_quorabay_answer_like);
            answer = itemView.findViewById(R.id.iv_quorabay_answer_answer);
            dislikes = itemView.findViewById(R.id.tv_quorabay_answer_dislikes);
            answers = itemView.findViewById(R.id.tv_quorabay_answer_answers);
            tv_post_questionText = itemView.findViewById(R.id.tv_post_questionText);
        }
    }
}
