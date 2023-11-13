package com.example.dungeongame.model;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dungeongame.R;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.behaviors.InputObserver;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.collisions.CollisionType;
import com.example.dungeongame.viewmodels.EndScreenViewModel;
import com.example.dungeongame.views.EndScreen;
import com.example.dungeongame.views.GameActivity;
import com.example.dungeongame.views.NewActivity;

import java.util.Date;

// Layout for the singleton user class
public class Player implements InputObserver, DrawableSprite {

    final int playerWidth = 100;
    private int playerX;
    private int playerY;
    private int playerHealth;
    private CollisionBox collider;
    private Bitmap sprite;
    private String sprite_string;

    private String difficultyLevel;

    private Player() {
        collider = new CollisionBox(0, 0, playerWidth, playerWidth, CollisionType.PLAYER);
        updatePosition(500, 500);
        playerHealth = 100;
        difficultyLevel = "";
    }

    private static Player instance = null;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    @Override
    public boolean attemptMove(int x, int y, int currentRoom) {
        int newX = playerX + x;
        int newY = playerY + y;
        CollisionType collisionType = CollisionManager.getInstance()
                .checkFutureCollisions(this, newX, newY);
        if (collisionType == CollisionType.NONE) {
            updatePosition(newX, newY);
        }
        else if (collisionType == CollisionType.ENEMY) {
            // Saarthak: change this based on the difficulty
            if (difficultyLevel.equals("Easy")) {
                reducePlayerHealth(5);
            }
            else if (difficultyLevel.equals("Medium")) {
                reducePlayerHealth(10);
            }
            else {
                reducePlayerHealth(15);
            }

            System.out.println("Enemy Collision Detected! Player Health: " + getPlayerHealth());
        } else if (collisionType == CollisionType.DOOR) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        if (sprite_string != null) {
            if (sprite_string.equals("pickle")) {
                sprite = BitmapFactory.decodeResource(GameActivity.resources, R.drawable.purpleplayer);
            } else if (sprite_string.equals("monkey")) {
                sprite = BitmapFactory.decodeResource(GameActivity.resources, R.drawable.greenplayer);
            } else if (sprite_string.equals("banana")) {
                sprite = BitmapFactory.decodeResource(GameActivity.resources, R.drawable.blueplayer);
            }
        }
        adjustDensity(sprite, playerWidth);
        canvas.drawBitmap(sprite, playerX, playerY, new Paint());
    }

    @Override
    public int getLayer() {
        return 1;
    }

    public void updatePosition(int x, int y) {
        playerX = x;
        playerY = y;
        collider.updatePostion(x, y);
    }

    public void setSprite(String Sprite) {
        sprite_string = Sprite;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    private void reducePlayerHealth(int amount) {
        playerHealth -= amount;
        if (playerHealth <= 0) {
            playerHealth = 0;

        }
    }



    // New method to get the CollisionBox of the player
    public CollisionBox getCollisionBox() {
        return collider;
    }
}
