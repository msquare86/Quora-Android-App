package com.example.quorabayactivity.quorabay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabayModeratorpageRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitFollower;
import com.example.quorabayactivity.quorabay.models.CorporateDeatails;
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

public class QuorabayModeratorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout quorabay_drawer_layout;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_moderator);


        toolbar =  findViewById(R.id.quora_home_moderator_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("QuoraBay");

        NavigationView navigationView = findViewById(R.id.quora_nav_moderator_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) QuorabayModeratorActivity.this);

        quorabay_drawer_layout = findViewById(R.id.quora_add_moderator_homepage_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, quorabay_drawer_layout, toolbar, R.string.quora_navigation_drawer_open, R.string.quora_navigation_drawer_close);

        quorabay_drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        floatingActionButton = findViewById(R.id.fav_quorabay_moderator_post_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoModerator = new Intent(QuorabayModeratorActivity.this , QuorabayPostQuestionActivity.class);
                startActivity(gotoModerator);
            }
        });

        List<Questions> questionsList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.quorabay_moderator_recyler_view);

        Retrofit retrofit = RetrofitFollower.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);
        List<CorporateDeatails> corporateDeatailsList = new ArrayList<>();
        Call<List<CorporateDeatails>> corporateDeatailsCall = iPostAPI.getPagesOfModerator("u2");
        corporateDeatailsCall.enqueue(new Callback<List<CorporateDeatails>>() {
            @Override
            public void onResponse(Call<List<CorporateDeatails>> call, Response<List<CorporateDeatails>> response) {
                if (response.body() != null){
                    for(CorporateDeatails corporateDeatails : response.body()){
                        corporateDeatailsList.add(corporateDeatails);
                    }
                }
                Log.d("corporate", "onResponse: " + corporateDeatailsList.size());
                QuorabayModeratorpageRecylerViewAdapter recyclerViewAdapter = new QuorabayModeratorpageRecylerViewAdapter(corporateDeatailsList, QuorabayModeratorActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuorabayModeratorActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<CorporateDeatails>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.quorabay_nav_moderator_logout:
                // TODO: go to log in page

        }
        return true;
    }
}