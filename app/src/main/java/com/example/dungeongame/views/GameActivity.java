package com.example.dungeongame.views;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeongame.R;
import com.example.dungeongame.model.Leaderboard;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.viewmodels.GameViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class GameActivity extends AppCompatActivity {
    private static final long DELAY_MILLIS = 100; // One second delay
    private int score = 1000; // Starting score
    private int currentRoom = 0;

    public static Resources resources;

    public static int canvasDensity = 440;
    ArrayList<DrawableSprite> drawables = new ArrayList<>();

    GameViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        resources = getResources();

        ImageView mainView = findViewById(R.id.game_view);
        Bitmap bitmap = Bitmap.createBitmap(1000, 2000, Bitmap.Config.ARGB_8888);
        canvasDensity = bitmap.getDensity();
        mainView.setImageBitmap(bitmap);


        Canvas gameCanvas = new Canvas(bitmap);
        vm = new GameViewModel(drawables);

        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        String selectedCharacter = intent.getStringExtra("selectedCharacter");
        String difficulty = intent.getStringExtra("selectedDifficulty");
        int startingHealth = getStartingHealthForDifficulty(difficulty);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (drawables != null) {
                    drawables.sort(Comparator.comparingInt(DrawableSprite::getLayer));
                    for (DrawableSprite d: drawables) {
                        d.draw(gameCanvas);
                    }
                }
                mainView.invalidate();
                if (vm.GameFinished()) {
                    Leaderboard.getLeaderboard().addEntry(playerName, vm.getScore(), new Date());
                    Intent endingIntent = new Intent(GameActivity.this, EndScreen.class);
                    endingIntent.putExtra("score", score);
                    startActivity(endingIntent);
                    finish();
                }else {
                    handler.postDelayed(this, DELAY_MILLIS);
                }
            }
        }, DELAY_MILLIS);
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
        vm.getInput(keyCode, event);

        return true;
    }
}