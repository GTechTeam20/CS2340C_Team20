package com.example.dungeongame.views;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeongame.R;
import com.example.dungeongame.model.Leaderboard;
import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.enemy.Enemy;
import com.example.dungeongame.viewmodels.GameViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class GameActivity extends AppCompatActivity {
    private static final long DELAY_MILLIS = 100; // One second delay
    private int currentRoom = 0;

    private static Resources resources;

    private static int canvasDensity = 440;
    private ArrayList<DrawableSprite> drawables = new ArrayList<>();

    private GameViewModel vm;
    private TextView healthTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        resources = getResources();

        ImageView mainView = findViewById(R.id.game_view);
        Bitmap bitmap = Bitmap.createBitmap(1000, 2000, Bitmap.Config.ARGB_8888);
        canvasDensity = bitmap.getDensity();
        mainView.setImageBitmap(bitmap);
        healthTextView = findViewById(R.id.healthTextView);

        Canvas gameCanvas = new Canvas(bitmap);
        vm = new GameViewModel(drawables);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        String selectedCharacter = intent.getStringExtra("selectedCharacter");
        String difficulty = intent.getStringExtra("selectedDifficulty");
        int startingHealth = getStartingHealthForDifficulty(difficulty);
        Player playerInst = Player.getInstance();
        playerInst.setSprite(selectedCharacter);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (drawables != null) {
                    updateEnemies();
                    updateHealthText();
                    drawables.sort(Comparator.comparingInt(DrawableSprite::getLayer));
                    for (DrawableSprite d: drawables) {
                        d.draw(gameCanvas);
                    }
                }
                mainView.invalidate();
                if (vm.gameFinished() || Player.getInstance().getPlayerHealth() <= 0) {
                    Leaderboard.getLeaderboard().addEntry(playerName, vm.getScore(), new Date());
                    Intent endingIntent = new Intent(GameActivity.this, EndScreen.class);
                    endingIntent.putExtra("score", vm.getScore());
                    startActivity(endingIntent);
                    finish();
                } else {
                    handler.postDelayed(this, DELAY_MILLIS);
                }
            }
        }, DELAY_MILLIS);
    }
    private void updateEnemies() {
        for (DrawableSprite d : drawables) {
            if (d != null && d instanceof Enemy) {
                ((Enemy) d).updatePosition();
            }
        }
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
    private void updateHealthText() {
        // Get the player's health from the Player instance
        int playerHealth = Player.getInstance().getPlayerHealth();

        // Update the health TextView
        healthTextView.setText("Health: " + playerHealth);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        vm.getInput(keyCode, event);
        return true;

    }

    public static Resources getResourcesRef() {
        return resources;
    }

    public static int getCanvasDensity() {
        return canvasDensity;
    }
}
