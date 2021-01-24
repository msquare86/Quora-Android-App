package com.example.quorabayactivity.quorabay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayPostQuestionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner;
    private EditText questionBox;
    private Button cancelButton, postQuestionButton;
    private String askedByName = "";
    private ProgressDialog loader;
    private String myUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_post_question);
        toolbar = findViewById(R.id.quorabay_postquestion_question_toolbar);
        //getSupportActionBar().setTitle("Post a question");

        spinner = findViewById(R.id.quorabay__postquestion_spinner);
        questionBox = findViewById(R.id.et_quorabay_postquestion_askQuestion);
        cancelButton = findViewById(R.id.btn_quorabay_postquestion_cancel);
        postQuestionButton = findViewById(R.id.btn_quorabay_postquestion_postQuestion);
        loader = new ProgressDialog(this);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.topics));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QuorabayPostQuestionActivity.this, "clicked" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(QuorabayPostQuestionActivity.this, "Select 1 Item", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuorabayPostQuestionActivity.this, QuorabayHomeActivity.class);
            startActivity(intent);
            //finish();
        });


        postQuestionButton.setOnClickListener(v -> {
//            if (getQuestionText().isEmpty()) {
//                questionBox.setError("Question Required!");
//            }
//            else if ((getTopic().equals("select topic"))) {
//                Toast.makeText(this, "Select one Field", Toast.LENGTH_SHORT).show();
//            }
//            else if (!getQuestionText().isEmpty() && !getTopic().equals("")) {
//                Toast.makeText(this, "Enter required fields", Toast.LENGTH_SHORT).show();
//            }
//            else{

                EditText editText = findViewById(R.id.et_quorabay_postquestion_askQuestion);
                Questions questions = new Questions();
                questions.setCategoryId("c2");
                questions.setCorporateId("c1");
                questions.setUserId("u3");
                questions.setQuestionText(editText.getText().toString());

                Call<ResponseBody> responseBodyCall = iPostAPI.createQuestion(questions);

                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(QuorabayPostQuestionActivity.this , "Question Successfully Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(QuorabayPostQuestionActivity.this,QuorabayHomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(QuorabayPostQuestionActivity.this, "Failed" + t, Toast.LENGTH_LONG).show();
                        Log.d("Manan" , "T");
                    }
                });
//            }

        });

    }
    String getQuestionText()
    {
        return questionBox.getText().toString().trim();
    }

    String getTopic()
    {
        return spinner.getSelectedItem().toString();
    }


    private void startLoader() {
        loader.setMessage("Posting your question");
        loader.setCanceledOnTouchOutside(false);
        loader.show();
    }
}