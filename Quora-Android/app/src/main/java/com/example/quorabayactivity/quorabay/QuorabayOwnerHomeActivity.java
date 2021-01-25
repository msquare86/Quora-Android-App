package com.example.quorabayactivity.quorabay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quorabayactivity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuorabayOwnerHomeActivity extends AppCompatActivity  {
    TextView moderatorlist;
    SearchView searchView;

    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_owner_home);

        moderatorlist = findViewById(R.id.tv_quorabay_owner_moderatorlist);
        moderatorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoModeratorList = new Intent(QuorabayOwnerHomeActivity.this , QuorabayOwnerModeratorList.class);
                startActivity(gotoModeratorList);
            }
        });

        searchView = findViewById(R.id.quorabay_owner_search);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuorabayOwnerHomeActivity.this , QuorabaySearchModeratorActivity.class);
                startActivity(intent);
            }
        });
    }
}