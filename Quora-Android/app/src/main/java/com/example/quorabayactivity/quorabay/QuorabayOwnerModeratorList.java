package com.example.quorabayactivity.quorabay;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quorabayactivity.R;
import com.example.quorabayactivity.quorabay.builders.RetrofitBuilder;
import com.example.quorabayactivity.quorabay.models.Answers;
import com.example.quorabayactivity.quorabay.networks.IPostAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class QuorabayOwnerModeratorList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_owner_moderator_list);

        List<Answers> answersList  = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.row_quorabay_owner_moderator_list_recycler_view);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        IPostAPI iPostAPI = retrofit.create(IPostAPI.class);


    }
}