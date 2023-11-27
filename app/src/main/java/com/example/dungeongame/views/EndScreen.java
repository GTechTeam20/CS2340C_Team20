package com.example.dungeongame.views;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dungeongame.R;
import com.example.dungeongame.model.Player;
import com.example.dungeongame.viewmodels.EndScreenViewModel;


public class EndScreen extends AppCompatActivity {

    private Button btnStartOver;
    private ListView lstLeaderboard;

    private TextView textView;

    private int health = Player.getInstance().getPlayerHealth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //textView = findViewById(R.id.textView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        btnStartOver = findViewById(R.id.btnStartOver);

        if (health <= 0) {
            textView = findViewById(R.id.textView);
            textView.setText("You Lose");
        }

        btnStartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player.deleteInstance();
                Intent intent = new Intent(EndScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        EndScreenViewModel viewModel = new EndScreenViewModel();

        lstLeaderboard = findViewById(R.id.lstScoreboard);
        lstLeaderboard.setAdapter(viewModel.getLeaderboard(this));
    }


}