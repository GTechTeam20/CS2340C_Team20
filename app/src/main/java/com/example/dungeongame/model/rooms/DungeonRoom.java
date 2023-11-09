package com.example.dungeongame.model.rooms;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionManager;

import java.util.ArrayList;

public abstract class DungeonRoom implements DrawableSprite {
    Bitmap background;
    ArrayList<CollisionBox> collisionBoxes = new ArrayList<>();
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, new Paint());
    }
    @Override
    public int getLayer() {
        return 0;
    }

    public void addWall(CollisionBox c) {
        collisionBoxes.add(c);
        CollisionManager.getInstance().addCollision(c);
    }
    public void deleteColliders() {
        for (CollisionBox c : collisionBoxes) {
            c.delete();
        }
    }
    public void deleteRoom() {
        deleteColliders();
    }


}
