package com.example.quorabayactivity.quorabay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.builders.RetrofitData;
import com.example.quorabayactivity.quorabay.dto.DataanalyticsPost;
import com.example.quorabayactivity.quorabay.models.Category;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.quorabayactivity.R.array.topics;

public class QuorabayPostQuestionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinner;
    private EditText questionBox;
    private Button cancelButton, postQuestionButton;
    private String askedByName = "";
    private ProgressDialog loader;
    private String myUrl="";
    private String categories[] = {"Technology" , "Food" , "Music" , "Entertainment" , "Sports"};
    private boolean flag = true;
    private String categoryName = "";
    HashMap<String , String> hashMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_post_question);
        toolbar = findViewById(R.id.quorabay_postquestion_question_toolbar);
        //getSupportActionBar().setTitle("Post a question");

        //spinner = findViewById(R.id.quorabay__postquestion_spinner);
        questionBox = findViewById(R.id.et_quorabay_postquestion_askQuestion);
        cancelButton = findViewById(R.id.btn_quorabay_postquestion_cancel);
        postQuestionButton = findViewById(R.id.btn_quorabay_postquestion_postQuestion);
        loader = new ProgressDialog(this);
        spinner = findViewById(R.id.quorabay__postquestion_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(topics));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        hashMap.put("Music" , "c1");
        hashMap.put("Sports" , "c2");
        hashMap.put("Technology" , "c3");
        hashMap.put("Entertainment" , "c4");
        hashMap.put("Food" , "c5");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    categoryName = categories[position - 1];
                }else{
                    Toast.makeText(QuorabayPostQuestionActivity.this, "Select Valid Field", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(QuorabayPostQuestionActivity.this, "Select Valid Field", Toast.LENGTH_SHORT).show();
            }
        });
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);

        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuorabayPostQuestionActivity.this, QuorabayHomeActivity.class);
            startActivity(intent);
        });

        postQuestionButton.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.et_quorabay_postquestion_askQuestion);
            Questions questions = new Questions();
            Category category = new Category();
            category.setCategoryId(hashMap.get(categoryName));
            category.setCategoryName(categoryName);
            questions.setCorporateId("c1");
            questions.setUserId("u3");
            questions.setQuestionText(editText.getText().toString());
            questions.setCategory(category);
            Call<ResponseBody> responseBodyCall = iPostAPI.createQuestion(questions);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(QuorabayPostQuestionActivity.this , "Question Successfully Added", Toast.LENGTH_LONG).show();
                    Retrofit retrofit1 = RetrofitData.getInstance();
                    IPostAPI iPostAPI1 = retrofit1.create(IPostAPI.class);
                    DataanalyticsPost dataanalyticsPost = new DataanalyticsPost();
                    dataanalyticsPost.setAction("post");
                    dataanalyticsPost.setCategoryName(categoryName);
                    dataanalyticsPost.setChannelId(1);
                    dataanalyticsPost.setType("text");
                    dataanalyticsPost.setPostId("1");
                    Call<ResponseBody> postAnalyticcall = iPostAPI1.postToAnalytics(dataanalyticsPost);
                    postAnalyticcall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("Data", "onResponse: nothing");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("Data Fail", "onFailure: " + t);
                        }
                    });
                    Intent intent = new Intent(QuorabayPostQuestionActivity.this,QuorabayHomeActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(QuorabayPostQuestionActivity.this, "Failed" + t, Toast.LENGTH_LONG).show();
                    Log.d("Manan" , "T");
                }
            });

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