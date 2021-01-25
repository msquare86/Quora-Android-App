package com.example.quorabayactivity.quorabay;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.adapters.QuorabaySearchRecylerViewAdapter;
import com.example.quorabayactivity.quorabay.builders.RetrofitSearch;
import com.example.quorabayactivity.quorabay.models.UserSearch;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuorabaySearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_search);

        Retrofit retrofitSearch = RetrofitSearch.getInstance();
        IPostAPI iPostAPI = retrofitSearch.create(IPostAPI.class);

        RecyclerView recyclerView = findViewById(R.id.search_RecyclerView);
        FloatingSearchView mSearchView = findViewById(R.id.fsv_quorabay_search_floatingSearch);

        mSearchView.setOnQueryChangeListener((oldQuery, newQuery) -> {
            Log.d("search", "onCreate: "+ newQuery);
            Call<List<UserSearch>> searchCall = iPostAPI.getAll(newQuery);
            searchCall.enqueue(new Callback<List<UserSearch>>() {
                @Override
                public void onResponse(Call<List<UserSearch>> call, Response<List<UserSearch>> response) {
                    Log.d("search", "onResponse: "+ response.body());
                    if(!(response.body() == null)){
                        QuorabaySearchRecylerViewAdapter recyclerViewAdapter = new QuorabaySearchRecylerViewAdapter(response.body(), QuorabaySearchActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(QuorabaySearchActivity.this));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }

                }

                @Override
                public void onFailure(Call<List<UserSearch>> call, Throwable t) {
                    Log.d("Search", "onFailure: "+t);
                }
            });
        });

    }
}