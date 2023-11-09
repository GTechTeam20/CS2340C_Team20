package com.example.dungeongame.model.behaviors;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.dungeongame.views.GameActivity;

public interface DrawableSprite {

    Paint defaultPaint = new Paint();
    public abstract void draw(Canvas canvas);

    default void adjustDensity(Bitmap bitmap, int intendedWidth) {
        // Android studio scales bitmaps when they are loaded in, this fixes it
        int test = bitmap.getWidth();
        bitmap.setDensity(bitmap.getWidth() * GameActivity.canvasDensity / intendedWidth);
    }

    int getLayer();

}
