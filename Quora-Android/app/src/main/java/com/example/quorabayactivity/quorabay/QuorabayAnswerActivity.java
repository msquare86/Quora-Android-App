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
import com.example.quorabayactivity.quorabay.adapters.QuorabayAnswersRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.dto.PostAnswer;
import com.example.quorabayactivity.quorabay.models.Answers;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_answer);

        List<Answers> answersList  = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.row_quorabay_answer_recycler_view);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);
        String questionId = (String) getIntent().getSerializableExtra("QuestionId");
        String questionText = (String) getIntent().getSerializableExtra("QuestionText");
        Call<List<Answers>> answersApiCall = iPostAPI.getAnswersByQuestionId(questionId);
        answersApiCall.enqueue(new Callback<List<Answers>>() {
            @Override
            public void onResponse(Call<List<Answers>> call, Response<List<Answers>> response) {
                Log.d("Response" , response.body().toString());
                for (Answers answers : response.body()){
                    Log.d("Response", "onResponse: sfsfs");
                    answersList.add(answers);
                }

                Log.d("Question page", "onResponse: " + answersList.size());
                QuorabayAnswersRecylerViewAdapter recyclerViewAdapter = new QuorabayAnswersRecylerViewAdapter(answersList, QuorabayAnswerActivity.this, questionId, questionText);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayAnswerActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
            @Override
            public void onFailure(Call<List<Answers>> call, Throwable t) {
                Log.e("Fail", "onFailure: "+t );
            }
        });
        TextView postAnswer = findViewById(R.id.tv_quora_answering_post);
        postAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.et_quora_adding_answer);
                PostAnswer postAnswer1 = new PostAnswer();
                postAnswer1.setUserId("u1");
                postAnswer1.setContent(editText.getText().toString());
                postAnswer1.setQuestionId(questionId);
                Call<ResponseBody> postAnswerApiCall = iPostAPI.createAnswer(postAnswer1);
                postAnswerApiCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(QuorabayAnswerActivity.this , "Answer Successfully Added", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(QuorabayAnswerActivity.this, "Failed" + t, Toast.LENGTH_LONG).show();
                        Log.d("Answer" , "T");
                    }
                });
            }
        });
    }
}