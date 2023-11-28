package com.example.dungeongame.model.powerups;

import android.graphics.Canvas;

import com.example.dungeongame.model.collisions.CollisionBox;

public interface Powerup {
    void draw(Canvas canvas);
    CollisionBox getCollisionBox();
}
