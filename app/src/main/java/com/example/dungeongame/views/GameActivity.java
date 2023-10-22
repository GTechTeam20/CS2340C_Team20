package com.example.dungeongame.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeongame.R;
import com.example.dungeongame.model.Leaderboard;
import com.example.dungeongame.model.Sprite;

import java.util.Date;

public class GameActivity extends AppCompatActivity {
    private static final long DELAY_MILLIS = 1000; // One second delay
    private int score = 10; // Starting score
    private int currentRoom = 0;

    private int x, y;
    private int[] roomBackgrounds = {
        R.drawable.room1,
        R.drawable.room2,
        R.drawable.room3
    };

    private ImageView imageViewCharacter;
    private Sprite sprite;
    private TextView textViewScore;
    private ImageView roomImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        String selectedCharacter = intent.getStringExtra("selectedCharacter");
        String difficulty = intent.getStringExtra("selectedDifficulty");
        int startingHealth = getStartingHealthForDifficulty(difficulty);

        TextView textViewPlayerName = findViewById(R.id.textViewPlayerName);
        textViewScore = findViewById(R.id.textViewScore);
        imageViewCharacter = findViewById(R.id.imageViewCharacter);
        sprite = new Sprite(10, 10, imageViewCharacter);
        roomImageView = findViewById(R.id.roomImageView);

        textViewPlayerName.setText("Player Name: " + playerName);
        textViewScore.setText("Score: " + score);

        setCharacterImage(selectedCharacter);
        setRoomBackground(currentRoom);

        Button btnNextRoom = findViewById(R.id.btnNextRoom);
        btnNextRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentRoom < roomBackgrounds.length - 1) {
                    currentRoom++;
                    setRoomBackground(currentRoom);
                }
                //else {
                // Implement what happens when all rooms are completed

                //}
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (score > 0) {
                    score -= 1;
                    updateScore();
                    handler.postDelayed(this, DELAY_MILLIS);
                } else {
                    Leaderboard.getLeaderboard().addEntry(playerName, 0, new Date());
                    Intent endingIntent = new Intent(GameActivity.this, EndScreen.class);
                    endingIntent.putExtra("score", 0);
                    startActivity(endingIntent);
                    finish();
                }
            }
        }, DELAY_MILLIS);
    }

    private void setRoomBackground(int roomNumber) {
        if (roomNumber >= 0 && roomNumber < roomBackgrounds.length) {
            roomImageView.setImageResource(roomBackgrounds[roomNumber]);
        }
    }

    private void setCharacterImage(String selectedCharacter) {
        if ("monkey".equals(selectedCharacter)) {
            imageViewCharacter.setImageResource(R.drawable.monkey);
        } else if ("pickle".equals(selectedCharacter)) {
            imageViewCharacter.setImageResource(R.drawable.pickle);
        } else if ("banana".equals(selectedCharacter)) {
            imageViewCharacter.setImageResource(R.drawable.banana);
        }
    }

    private void updateScore() {
        textViewScore.setText("Score: " + score);
    }
    public int getStartingHealthForDifficulty(String difficulty) {
        int startingHealth = 0;

        if ("Easy".equals(difficulty)) {
            startingHealth = 100;
        } else if ("Medium".equals(difficulty)) {
            startingHealth = 75;
        } else if ("Hard".equals(difficulty)) {
            startingHealth = 50;
        }

        return startingHealth;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                x -= 20;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                x += 20;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                y -= 20;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                y += 20;
                break;
        }
        sprite.updateSpritePosition(x, y);
        return true;
    }
}