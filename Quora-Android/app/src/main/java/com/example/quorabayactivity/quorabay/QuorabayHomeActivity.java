package com.example.quorabayactivity.quorabay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayQuestionsRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.models.Questions;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabayHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout quorabay_drawer_layout;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private String userId;
    private String userName;
    private String userEmail;
    private String userImage;
    //private SwipeRefreshLayout swipeRefreshLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_home);
        floatingActionButton = findViewById(R.id.quorabay_fab);
        toolbar =  findViewById(R.id.quora_home_toolbar);


        TextView tv_email = findViewById(R.id.quorabay_nav_header_email);
        TextView quorabay_nav_header_username = findViewById(R.id.quorabay_nav_header_username);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("QuoraBay");
        NavigationView navigationView = findViewById(R.id.quora_nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) QuorabayHomeActivity.this);
        quorabay_drawer_layout = findViewById(R.id.quorabay_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, quorabay_drawer_layout, toolbar, R.string.quora_navigation_drawer_open, R.string.quora_navigation_drawer_close);
        quorabay_drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        userId = getIntent().getStringExtra("QuorabayUserId");
        userName = getIntent().getStringExtra("QuorabayUserName");
        userEmail = getIntent().getStringExtra("QuorabayUserEmail");
        userImage = getIntent().getStringExtra("QuorabayUserImage");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuorabayHomeActivity.this, QuorabayPostQuestionActivity.class);
                intent.putExtra("QuorabayUserId" , userId);
                intent.putExtra("QuorabayUserName" , userName);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.quorabay_nav_search);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuorabayHomeActivity.this , QuorabaySearchActivity.class);
                startActivity(intent);
            }
        });
        List<Questions> questionsList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.quorabay_recycler_view);
        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);
        Call<List<Questions>> questionApiCall = iPostAPI.getQuestions();
        questionApiCall.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                Log.d("Response" , response.body().toString());
                for (Questions questions : response.body()){
                    Log.d("Question Response", "onResponse: user");
                    questionsList.add(questions);
                }
                Log.d("Question page", "onResponse: " + questionsList.size());
                QuorabayQuestionsRecylerViewAdapter recyclerViewAdapter = new QuorabayQuestionsRecylerViewAdapter(questionsList, QuorabayHomeActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayHomeActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Log.e("Fail", "onFailure: "+t );
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (quorabay_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            quorabay_drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.quorabay_nav_technology:
                Intent technology = new Intent(QuorabayHomeActivity.this, QuorabayTechnologyActivity.class);
                startActivity(technology);
                break;

            case R.id.quorabay_nav_food:
                Intent food = new Intent(QuorabayHomeActivity.this , QuorabayFoodActivity.class);
                startActivity(food);
                break;

            case R.id.quorabay_nav_music:
                Intent music = new Intent(QuorabayHomeActivity.this , QuorabayMusicActivity.class);
                startActivity(music);
                break;

            case R.id.quorabay_nav_entertainment:
                Intent entertainment = new Intent(QuorabayHomeActivity.this , QuorabayEntertainmentActivity.class);
                startActivity(entertainment);
                break;

            case R.id.quorabay_nav_sports:
                Intent sports = new Intent(QuorabayHomeActivity.this , QuorabaySportsActivity.class );
                startActivity(sports);
                break;

            case R.id.quorabay_nav_followers:
                Intent followers = new Intent(QuorabayHomeActivity.this , QuorabayFollowerListActivity.class);
                followers.putExtra("QuorabayUserId" , userId);
                startActivity(followers);
                break;

            case R.id.quorabay_nav_follow_request:
                Intent followRequest = new Intent(QuorabayHomeActivity.this , QuorabayUserFollowRequestActivity.class);
                startActivity(followRequest);
                break;

            case R.id.quorabay_nav_logout:
                Intent logout = new Intent(QuorabayHomeActivity.this , LoginActivity.class);
                logout.putExtra("channelId" , 1);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logout);
                break;

            case R.id.quorabay_nav_myquestions:
                Intent gotoQuestions = new Intent(QuorabayHomeActivity.this , QuorabayMyQuestionsActivity.class);
                gotoQuestions.putExtra("QuorabayUserId" , userId);
                gotoQuestions.putExtra("QuorabayUserName" , userName);
                startActivity(gotoQuestions);
                break;

            case R.id.quorabay_nav_profile:
                Intent gotoProfile = new Intent(QuorabayHomeActivity.this , QuorabayUserProfileActivity.class);
                gotoProfile.putExtra("QuorabayUserId" , userId);
                gotoProfile.putExtra("QuorabayUserName" , userName);
                gotoProfile.putExtra("QuorabayUserImage" , userImage);
                startActivity(gotoProfile);
                break;
        }
        quorabay_drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

}