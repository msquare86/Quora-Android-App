package com.example.quorabayactivity.quorabay;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayCommentsRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.dto.CommentData;
import com.example.quorabayactivity.quorabay.dto.PostComment;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayCommentsActivity extends AppCompatActivity {

    TextView postComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_comments);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        RecyclerView recyclerView = findViewById(R.id.row_quorabay_comment_recycler_view);
        String answerId = (String) getIntent().getSerializableExtra("AnswerId");
        String answerText = (String) getIntent().getSerializableExtra("AnswerText");
        Log.d("ABC", "onCreate: " + answerId);
        List<CommentData> commentsList = new ArrayList<>();
        Call<List<CommentData>> responseBodyCall = iPostAPI.getCommentByAnswerId(answerId);
        responseBodyCall.enqueue(new Callback<List<CommentData>>() {
            @Override
            public void onResponse(Call<List<CommentData>> call, Response<List<CommentData>> response) {
                Log.d("Response" , response.body().toString());
                for (CommentData commentData : response.body()){
                    Log.d("Response", "onResponse:" + commentData);
                    commentsList.add(commentData);
                }

                Log.d("Comment page", "onResponse: " + commentsList.size());
                QuorabayCommentsRecylerViewAdapter recyclerViewAdapter = new QuorabayCommentsRecylerViewAdapter(commentsList, QuorabayCommentsActivity.this, answerId, answerText);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayCommentsActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<CommentData>> call, Throwable t) {

            }
        });

        postComment = findViewById(R.id.tv_quora_commenting_post);
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et_quora_adding_comment);
                PostComment postComment = new PostComment();
                Log.d("postComment", "onClick: " + editText.getText().toString());
                postComment.setCommentText(editText.getText().toString());
                postComment.setUserId("mm");
                postComment.setAnswerId(answerId);
                //postComment.setParentId("aass");
                Call<ResponseBody> responseBodyCall1 = iPostAPI.createComment(postComment);
                responseBodyCall1.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(QuorabayCommentsActivity.this , "Comment Successfully Added", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(QuorabayCommentsActivity.this, "Failed" + t, Toast.LENGTH_LONG).show();
                        Log.d("Answer" , "T");
                    }
                });
            }
        });
    }
}