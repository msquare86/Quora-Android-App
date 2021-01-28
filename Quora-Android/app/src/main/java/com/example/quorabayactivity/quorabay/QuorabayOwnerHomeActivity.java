package com.example.quorabayactivity.quorabay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quorabayactivity.R;

public class QuorabayOwnerHomeActivity extends AppCompatActivity   {
    Button moderatorlist;
    Button profile;
    Button logout;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quorabay_owner_home);

        String ownerId = getIntent().getStringExtra("QuorabayOwnerId");
        String ownerName = getIntent().getStringExtra("QuorabayOwnerName");
        String ownerEmail = getIntent().getStringExtra("QuorabayOwnerEmail");
        String ownerImage = getIntent().getStringExtra("QuorabayOwnerImage");

        moderatorlist = findViewById(R.id.btn_quorabay_owner_moderatorlist);
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

        profile = findViewById(R.id.btn_quorabay_owner_profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuorabayOwnerHomeActivity.this , QuorabayUserProfileActivity.class);
                intent.putExtra("QuorabayUserId" , ownerId);
                intent.putExtra("QuorabayUserName" , ownerName);
                intent.putExtra("QuorabayUserImage" , ownerImage);
                intent.putExtra("QuorabayUserEmail" , ownerEmail);
                startActivity(intent);
            }
        });

        logout = findViewById(R.id.btn_quorabay_owner_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(QuorabayOwnerHomeActivity.this , LoginActivity.class);
                finish();
                logout.putExtra("channelId" , 1);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logout);
            }
        });
    }

}