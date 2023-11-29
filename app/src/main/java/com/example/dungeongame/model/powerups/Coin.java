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
import com.example.dungeongame.views.GameActivity;

public class Coin implements DrawableSprite, Powerup {
    private int x;
    private int y;
    private String imageName;
    private int width = 100;

    // CollisionBox associated with the Ghost
    private CollisionBox collider;

    private Coin(int x, int y, String imageName) {
        this.imageName = imageName;
        this.x = x;
        this.y = y;

        // Initialize the CollisionBox
        collider = new CollisionBox(x, y, width, width, CollisionType.COIN);
    }

    private static Coin instance = null;

    public static Coin getInstance(int x, int y, String imageName) {
        if (instance == null) {
            instance = new Coin(x, y, imageName);
        }

        return instance;
    }


    @Override
    public void draw(Canvas canvas) {
        // Load enemy image from resources based on the imageName
        Bitmap enemyBitmap = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                R.drawable.coin);
        adjustDensity(enemyBitmap, 100);
        canvas.drawBitmap(enemyBitmap, x, y, new Paint());
    }
    public void undraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        canvas.drawRect(x, y, x + width, y + width, paint);
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

