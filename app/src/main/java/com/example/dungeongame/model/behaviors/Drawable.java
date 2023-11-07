package com.example.dungeongame.model.behaviors;

import android.graphics.Canvas;

public interface Drawable {
    public abstract void draw(Canvas canvas);

    int getLayer();

}
