package com.example.dungeongame.model.behaviors;

import android.graphics.Canvas;

public interface Drawable {
    public int layer = 0;
    public abstract void draw(Canvas canvas);
}
