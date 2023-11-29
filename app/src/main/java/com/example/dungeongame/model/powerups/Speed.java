package com.example.dungeongame.model.powerups;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.dungeongame.R;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionType;
import com.example.dungeongame.model.enemy.Crab;
import com.example.dungeongame.views.GameActivity;

public class Speed implements DrawableSprite, Powerup {
    private int X;
    private int Y;
    private String imageName;
    private int width = 100;
    private CollisionBox collider;

    private Speed(int x, int y, String imageName) {
        this.imageName = imageName;
        this.X = x;
        this.Y = y;

        // Initialize the CollisionBox
        collider = new CollisionBox(x, y, width, width, CollisionType.SPEED);
    }

    private static Speed instance = null;

    public static Speed getInstance(int x, int y, String imageName) {
        if (instance == null) {
            instance = new Speed(x, y, imageName);
        }

        return instance;
    }


    @Override
    public void draw(Canvas canvas) {
        // Load enemy image from resources based on the imageName
        Bitmap enemyBitmap = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                R.drawable.speedboost);
        adjustDensity(enemyBitmap, 100);
        canvas.drawBitmap(enemyBitmap, X, Y, new Paint());
    }

    @Override
    public int getLayer() {
        return 1; // Adjust the layer as needed
    }

    public CollisionBox getCollisionBox() {
        // Provide the associated CollisionBox
        return collider;
    }
}
