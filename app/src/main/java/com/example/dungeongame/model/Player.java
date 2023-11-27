package com.example.dungeongame.model;

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
import com.example.dungeongame.views.GameActivity;


// Layout for the singleton user class
public class Player implements InputObserver, DrawableSprite {

    private final int playerWidth = 80;
    private final int playerHeight = 120;
    private int playerX;
    private int playerY;
    private int playerHealth;
    private CollisionBox collider;
    private Bitmap sprite;
    private String spriteString;
    private String difficultyLevel;
    private Sword sword;

    private Player() {
        collider = new CollisionBox(0, 0, playerWidth, playerHeight, CollisionType.PLAYER);
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

    public static Player deleteInstance() {
        instance = new Player();
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
        } else if (collisionType == CollisionType.ENEMY) {
            // Saarthak: change this based on the difficulty
            if (difficultyLevel.equals("Easy")) {
                reducePlayerHealth(5);
            } else if (difficultyLevel.equals("Medium")) {
                reducePlayerHealth(10);
            } else {
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
        if (spriteString != null) {
            if (spriteString.equals("pickle")) {
                sprite = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                        R.drawable.purpleplayer);
            } else if (spriteString.equals("monkey")) {
                sprite = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                        R.drawable.greenplayer);
            } else if (spriteString.equals("banana")) {
                sprite = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                        R.drawable.blueplayer);
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

    public void setSprite(String sprite) {
        spriteString = sprite;
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
    public void setSword(Sword sword) {
        this.sword = sword;
    }
    public void swingSword() {
        sword.swing(playerX, playerY);
    }

    // New method to get the CollisionBox of the player
    public CollisionBox getCollisionBox() {
        return collider;
    }
}
