package com.example.dungeongame.model.enemy;

import android.graphics.Canvas;

import com.example.dungeongame.model.collisions.CollisionBox;

public interface Enemy {

    void updatePosition();

    void draw(Canvas canvas);
    CollisionBox getCollisionBox();
}
