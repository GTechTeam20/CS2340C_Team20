package com.example.dungeongame;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


public class EndScreen extends AppCompatActivity {

    private Button btnStartOver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        btnStartOver = findViewById(R.id.btnStartOver);

        btnStartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}