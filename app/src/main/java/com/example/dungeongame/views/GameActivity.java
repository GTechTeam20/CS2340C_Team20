package com.example.dungeongame.views;

import android.content.Intent;
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
    private static final long DELAY_MILLIS = 100; // One second delay
    private int score = 1000; // Starting score
    private int currentRoom = 0;

    private boolean endGame = false;

    private int playerX;
    private int playerY;

    private int[][] startingPosition = {
            {100, 700},
            {100, 700},
            {100, 700}};

    private int[] roomBackgrounds = {
        R.drawable.room1,
        R.drawable.room2,
        R.drawable.room3
    };

    private ImageView imageViewCharacter;
    private Sprite playerSprite;
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
        playerSprite = new Sprite(10, 10, imageViewCharacter);
        playerX = startingPosition[currentRoom][0];
        playerY = startingPosition[currentRoom][1];
        roomImageView = findViewById(R.id.roomImageView);

        textViewPlayerName.setText("Player Name: " + playerName);
        textViewScore.setText("Score: " + score);

        setCharacterImage(selectedCharacter);
        setRoomBackground(currentRoom);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (score > 0 && !endGame) {
                    if (score == 1000) {
                        playerSprite.updateSpritePosition(playerX, playerY);
                    }
                    score -= 1;
                    updateScore();
                    handler.postDelayed(this, DELAY_MILLIS);
                    // textViewPlayerName.setText("Player X: " + playerX + "\nPlayer Y: " + playerY);
                } else {
                    Leaderboard.getLeaderboard().addEntry(playerName, score, new Date());
                    Intent endingIntent = new Intent(GameActivity.this, EndScreen.class);
                    endingIntent.putExtra("score", score);
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
        int newX = playerX;
        int newY = playerY;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                newX -= 20;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                newX += 20;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                newY -= 20;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                newY += 20;
                break;
        }

        int collision = checkCollisions(newX, newY);
        if (collision == 2){
            currentRoom++;
            if (currentRoom == 3) {
                endGame = true;
            } else {
                setRoomBackground(currentRoom);
                playerX = startingPosition[currentRoom][0];
                playerY = startingPosition[currentRoom][1];
                playerSprite.updateSpritePosition(playerX, playerY);
            }
        }
        if (collision == 1) {
            playerX = newX;
            playerY = newY;
            playerSprite.updateSpritePosition(playerX, playerY);
        }
        return true;
    }

    public int checkCollisions(int x, int y) {
        // wall = 0, open = 1, exit = 2
        if (currentRoom == 0) {
            if ((x < 500 && x > -400) && (y > 400 && y < 800)) {
                return 1;
            }
            if (y > 500 && y < 700 && x <= -400) {
                return 2;
            }
            return 0;
        }
        if (currentRoom == 1) {
            if ((x < 500 && x > -400) && (y > 400 && y < 800)) {
                return 1;
            }
            if (x > -100 && x < 100) {
                return 2;
            }
            return 0;
        }
        if (currentRoom == 2) {
            if ((x < 500 && x > -400) && (y > 450 && y < 800)) {
                return 1;
            }
            if (y > 500 && y < 700 && x <= -400) {
                return 2;
            }
            return 0;
        }
        return 0;
    }
    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
}