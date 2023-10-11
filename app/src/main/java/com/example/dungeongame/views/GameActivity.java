package com.example.dungeongame.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeongame.R;
import com.example.dungeongame.model.Leaderboard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameActivity extends AppCompatActivity {
    private static final long DELAY_MILLIS = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        // Retrieve selected character
        String selectedCharacter = intent.getStringExtra("selectedCharacter");
        String difficulty = intent.getStringExtra("selectedDifficulty");
        int startingHealth = 0;
        if (difficulty.equals("Easy")) {
            startingHealth = 100;
        } else if (difficulty.equals("Medium")) {
            startingHealth = 75;
        } else {
            startingHealth = 50;
        }

        // Populate TextViews and ImageView
        TextView textViewPlayerName = findViewById(R.id.textViewPlayerName);
        ImageView imageViewCharacter = findViewById(R.id.imageViewCharacter);
        TextView textViewDifficulty = findViewById(R.id.textViewDifficulty);
        TextView textViewStartingHealth = findViewById(R.id.textViewStartingHealth);

        textViewPlayerName.setText("Player Name: " + playerName);
        textViewDifficulty.setText("Difficulty: " + difficulty);
        textViewStartingHealth.setText("Starting Health: " + startingHealth);

        // Button to navigate to the ending screen
        Button btnEndGame = findViewById(R.id.btnEndGame);
        if ("monkey".equals(selectedCharacter)) {
            imageViewCharacter.setImageResource(R.drawable.monkey);
        } else if ("pickle".equals(selectedCharacter)) {
            imageViewCharacter.setImageResource(R.drawable.pickle);
        } else if ("banana".equals(selectedCharacter)) {
            imageViewCharacter.setImageResource(R.drawable.banana);
        }
        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the ending screen
                Leaderboard.getLeaderboard().AddEntry(playerName, 100, new Date());
                Intent endingIntent = new Intent(GameActivity.this, EndScreen.class);
                startActivity(endingIntent);
                finish(); // Finish this activity
            }
        });
    }
}
