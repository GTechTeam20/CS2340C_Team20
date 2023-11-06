package com.example.dungeongame.views;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeongame.R;
import com.example.dungeongame.model.Leaderboard;
import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.Sprite;
import com.example.dungeongame.model.behaviors.Drawable;
import com.example.dungeongame.model.behaviors.InputObserver;
import com.example.dungeongame.viewmodels.GameViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class GameActivity extends AppCompatActivity {
    private static final long DELAY_MILLIS = 100; // One second delay
    private int score = 1000; // Starting score
    private int currentRoom = 0;

    private boolean endGame = false;

    private int playerX = 100;
    private int playerY = 700;

    private int[][] startingPosition = {
            {100, 700},
            {100, 700},
            {100, 700}};

    private InputObserver inputSubscriber;

    private int[] roomBackgrounds = {
        R.drawable.room1,
        R.drawable.room2,
        R.drawable.room3
    };

    private ImageView imageViewCharacter;
    private Sprite playerSprite;
    private TextView textViewScore;
    private ImageView roomImageView;

    public static Resources resources;
    ArrayList<Drawable> drawables = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        resources = getResources();

        ImageView mainView = findViewById(R.id.game_view);
        Bitmap bitmap = Bitmap.createBitmap(1000, 2000, Bitmap.Config.ARGB_8888);
        mainView.setImageBitmap(bitmap);

        Canvas gameCanvas = new Canvas(bitmap);
        GameViewModel vm = new GameViewModel(drawables);



        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        String selectedCharacter = intent.getStringExtra("selectedCharacter");
        String difficulty = intent.getStringExtra("selectedDifficulty");
        int startingHealth = getStartingHealthForDifficulty(difficulty);



        // setCharacterImage(selectedCharacter);
        // setRoomBackground(currentRoom);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawables.sort(Comparator.comparingInt(o -> o.layer));
                if (drawables != null) {
                    for (Drawable d: drawables) {
                        d.draw(gameCanvas);
                    }
                }
                mainView.invalidate();
                if (1 != 2) {
                    return;
                }

                if (score < 0 && score > 0 && !endGame) {
                    score -= 1;
                    updateScore();
                    handler.postDelayed(this, DELAY_MILLIS);
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

    public void setCharacterImage(String selectedCharacter) {
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
        int newX = 0;
        int newY = 0;
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
        default:
            break;
        }

        if (newX != 0 || newY != 0) {
            if (inputSubscriber.attemptMove(newX, newY, currentRoom)) {
                currentRoom++;
                if (currentRoom == 3) {
                    endGame = true;
                } else {
                    setRoomBackground(currentRoom);
                    Player.getInstance().resetPosition(currentRoom);
                }
            }
        }

        return true;
    }
}