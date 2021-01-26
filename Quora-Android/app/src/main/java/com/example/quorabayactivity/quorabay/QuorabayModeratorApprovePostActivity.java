package com.example.quorabayactivity.quorabay;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayModeratorApprovePostRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.Answers;
import com.example.quorabayactivity.quorabay.models.QuestionRequest;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayModeratorApprovePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_moderator_approve_post);

        List<Answers> answersList  = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.row_quorabay_moderator_approvePost_recylerView);

        String corporateId = getIntent().getStringExtra("corporateId");

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        List<QuestionRequest> questionRequestList = new ArrayList<>();

        Call<List<QuestionRequest>> questionRequestApiCall = iPostAPI.getApprovePostRequestById(corporateId);
        questionRequestApiCall.enqueue(new Callback<List<QuestionRequest>>() {
            @Override
            public void onResponse(Call<List<QuestionRequest>> call, Response<List<QuestionRequest>> response) {
                Log.d("Response" , response.body().toString());
                if (response.body() != null) {
                    for (QuestionRequest questionRequest : response.body()) {
                        Log.d("Response", "onResponse:" + questionRequest);
                        questionRequestList.add(questionRequest);
                    }
                }
                Log.d("Approve", "onResponse: " + questionRequestList.size());
                QuorabayModeratorApprovePostRecylerViewAdapter recyclerViewAdapter = new QuorabayModeratorApprovePostRecylerViewAdapter(questionRequestList, QuorabayModeratorApprovePostActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayModeratorApprovePostActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<QuestionRequest>> call, Throwable t) {

            }
        });
    }
}