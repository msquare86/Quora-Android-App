package com.example.quorabayactivity.quorabay;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayQuestionsRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayMyQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_my_questions);

        String userId = getIntent().getStringExtra("QuorbayUserId");
        String userName = getIntent().getStringExtra("QuorbayUserName");

        List<Questions> questionsList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.row_quorabay_my_questions_recycler_view);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        Call<List<Questions>> questionsApiCall = iPostAPI.getAllQuestionsByUserId(userId);
        questionsApiCall.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if (response.body() != null){
                    for (Questions questions : response.body()){
                        Log.d("Question Response", "onResponse: user");
                        questionsList.add(questions);
                    }
                    Log.d("Question page", "onResponse: " + questionsList.size());
                    QuorabayQuestionsRecylerViewAdapter recyclerViewAdapter = new QuorabayQuestionsRecylerViewAdapter(questionsList, QuorabayMyQuestionsActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayMyQuestionsActivity.this));
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Log.e("Fail Question", "onFailure: " + t );
            }
        });

    }
}