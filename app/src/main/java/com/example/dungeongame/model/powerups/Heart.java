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

public class Heart implements DrawableSprite, Powerup {
    private int X;
    private int Y;
    private String imageName;
    private int width = 100;

    // CollisionBox associated with the Ghost
    private CollisionBox collider;

    private Heart(int x, int y, String imageName) {
        this.imageName = imageName;
        this.X = x;
        this.Y = y;

        // Initialize the CollisionBox
        collider = new CollisionBox(x, y, width, width, CollisionType.HEART);
    }

    private static Heart instance = null;

    public static Heart getInstance(int x, int y, String imageName) {
        if (instance == null) {
            instance = new Heart(x, y, imageName);
        }

        return instance;
    }


    @Override
    public void draw(Canvas canvas) {
        // Load enemy image from resources based on the imageName
        Bitmap enemyBitmap = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                R.drawable.heart);
        adjustDensity(enemyBitmap, 100);
        canvas.drawBitmap(enemyBitmap, X, Y, new Paint());
    }
    public void undraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        canvas.drawRect(X, Y, X + width, Y + width, paint);
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
