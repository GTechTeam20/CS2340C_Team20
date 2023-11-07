package com.example.dungeongame.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dungeongame.R;
import com.example.dungeongame.model.behaviors.Drawable;
import com.example.dungeongame.model.behaviors.InputObserver;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.collisions.CollisionType;
import com.example.dungeongame.views.GameActivity;

// Layout for the singleton user class
public class Player implements InputObserver, Drawable {
    private int playerX;
    private int playerY;
    public CollisionBox collider;
    private Bitmap sprite;

    private Player() {
        sprite = BitmapFactory.decodeResource(GameActivity.resources, R.drawable.monkey);
        collider = new CollisionBox(0, 0, 20, 20, CollisionType.PLAYER);
        updatePosition(500, 500);
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
        CollisionType collisionType = CollisionManager.getInstance()
                .checkFutureCollisions(collider, newX, newY);
        if (collisionType == CollisionType.NONE) {
            updatePosition(newX, newY);
        }
        if (collisionType == CollisionType.DOOR) {
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
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
}
