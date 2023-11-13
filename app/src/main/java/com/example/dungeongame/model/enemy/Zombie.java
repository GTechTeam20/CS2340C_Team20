package com.example.dungeongame.model.enemy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dungeongame.R;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionType;
import com.example.dungeongame.views.GameActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Zombie implements DrawableSprite, Enemy {

    private int enemyX;
    private int enemyY;
    private String imageName;
    private int speed = 5; // Adjust speed as needed
    private int minX, maxX, minY, maxY;
    private int enemyWidth = 100;

    // CollisionBox associated with the Ghost
    private CollisionBox collider;

    private Zombie(int x, int y, String imageName, int minX, int maxX, int minY, int maxY) {
        enemyX = x;
        enemyY = y;
        this.imageName = imageName;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;

        // Initialize the CollisionBox
        collider = new CollisionBox(x, y, enemyWidth, enemyWidth, CollisionType.ENEMY);
    }

    private static Zombie instance = null;

    public static Zombie getInstance(int x, int y, String imageName, int minX, int maxX, int minY, int maxY) {
        if (instance == null) {
            instance = new Zombie(x, y, imageName, minX, maxX, minY, maxY);
        }

        return instance;
    }

    @Override
    public void updatePosition() {
        // Move the ghost horizontally
        enemyX += speed;

        // Check boundaries for horizontal movement
        if (enemyX < minX || enemyX > maxX) {
            // Reverse direction if boundary is reached
            speed = -speed;
        }

        // Move the ghost vertically
        enemyY += 2 * speed;

        // Check boundaries for vertical movement
        if (enemyY < minY || enemyY > maxY) {
            // Reverse direction if boundary is reached
            speed = -speed;
        }

        // Update the CollisionBox position
        collider.updatePostion(enemyX, enemyY);
    }

    @Override
    public void draw(Canvas canvas) {
        // Load enemy image from resources based on the imageName
        Bitmap enemyBitmap = BitmapFactory.decodeResource(GameActivity.resources, R.drawable.zombie);
        adjustDensity(enemyBitmap, 100);
        canvas.drawBitmap(enemyBitmap, enemyX, enemyY, new Paint());
    }

    @Override
    public int getLayer() {
        return 1; // Adjust the layer as needed
    }

    @Override
    public CollisionBox getCollisionBox() {
        // Provide the associated CollisionBox
        return collider;
    }
}
