package com.example.dungeongame.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dungeongame.R;
import com.example.dungeongame.model.behaviors.Drawable;
import com.example.dungeongame.model.behaviors.InputObserver;
import com.example.dungeongame.views.GameActivity;

// Layout for the singleton user class
public class Player implements InputObserver, Drawable {
    private int playerX;
    private int playerY;

    private Bitmap sprite;
    private int[][] startingPosition = {
            {100, 700},
            {100, 700},
            {100, 700}};


    private Player() {
        sprite = BitmapFactory.decodeResource(GameActivity.resources, R.drawable.monkey);
        playerX = 500;
        playerY = 500;
    }
    private static Player instance = null;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }
    @Override
    public boolean attemptMove(int x, int y, int currentRoom) {
        int newX = playerX + x;
        int newY = playerY + y;
        int collision = checkCollisions(newX, newY, currentRoom);
        if (collision == 2) {
            return true;
        }
        if (collision == 1) {
            playerX = newX;
            playerY = newY;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite, playerX, playerY, new Paint());
    }

    public int checkCollisions(int x, int y, int currentRoom) {
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
    public void resetPosition(int room) {
        playerX = startingPosition[room][0];
        playerY = startingPosition[room][1];
    }

    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
}
