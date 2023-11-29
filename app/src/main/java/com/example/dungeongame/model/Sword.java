package com.example.dungeongame.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dungeongame.R;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.collisions.CollisionType;
import com.example.dungeongame.viewmodels.GameViewModel;
import com.example.dungeongame.views.GameActivity;

public class Sword implements DrawableSprite {
    private int swordX;
    private int swordY;

    private int xOffset = -50;

    private int yOffset = -100;

    private int framesRemaining = 0;

    private int activeFrames = 5;

    private int movementPerFrame = 8;

    private int width = 30;

    private int height = 100;

    // CollisionBox associated with the Ghost
    private CollisionBox collider;

    private Bitmap sprite;

    private GameViewModel vm;

    public Sword(GameViewModel vm) {
        this.vm = vm;
        // Initialize the CollisionBox
        collider = new CollisionBox(0, 0, width, height, CollisionType.SWORD);

        sprite = BitmapFactory.decodeResource(GameActivity.getResourcesRef(),
                R.drawable.sword);
        adjustDensity(sprite, 100);
    }

    @Override
    public void draw(Canvas canvas) {
        if (framesRemaining <= 0) {
            return;
        }
        swordX += movementPerFrame;
        framesRemaining -= 1;

        canvas.drawBitmap(sprite, swordX, swordY, new Paint());
        collider.updatePostion(swordX, swordY);

        CollisionBox enemy = CollisionManager.getInstance().checkSwordCollisions(collider);
        if (enemy != null) {
            vm.destroyEnemy(enemy);
        }
    }

    public void swing(int playerX, int playerY) {
        if (framesRemaining  > 0) {
            return;
        }
        swordX = playerX + xOffset;
        swordY = playerY + yOffset;
        collider.updatePostion(swordX, swordY);
        framesRemaining = activeFrames;
    }

    @Override
    public int getLayer() {
        return 2; // Adjust the layer as needed
    }
}
