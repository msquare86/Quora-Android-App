package com.example.quorabayactivity.quorabay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quorabayactivity.R;

public class ChoiceActivity extends AppCompatActivity {
    ImageView iv_choice_pagebook;
    ImageView iv_choice_quora;
    ImageView iv_choice_quiz;
    TextView tv_choice_pagebook;
    TextView tv_choice_quora;
    TextView tv_choice_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        iv_choice_pagebook = findViewById(R.id.iv_choice_pagebook);
        iv_choice_quora = findViewById(R.id.iv_choice_quora);
        iv_choice_quiz = findViewById(R.id.iv_choice_quiz);
        tv_choice_pagebook = findViewById(R.id.tv_choice_pagebook);
        tv_choice_quora = findViewById(R.id.tv_choice_quora);
        tv_choice_quiz = findViewById(R.id.tv_choice_quiz);


        iv_choice_pagebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LoginActivity.class);
                intent.putExtra("channelId", 0);
                startActivity(intent);
            }
        });

        iv_choice_quora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LoginActivity.class);
                intent.putExtra("channelId", 1);
                startActivity(intent);
            }
        });

        iv_choice_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LoginActivity.class);
                intent.putExtra("channelId", 2);
                startActivity(intent);
            }
        });

        tv_choice_pagebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LoginActivity.class);
                intent.putExtra("channelId", 0);
                startActivity(intent);
            }
        });

        tv_choice_quora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LoginActivity.class);
                intent.putExtra("channelId", 1);
                startActivity(intent);
            }
        });

        tv_choice_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, LoginActivity.class);
                intent.putExtra("channelId", 2);
                startActivity(intent);
            }
        });

    }
}