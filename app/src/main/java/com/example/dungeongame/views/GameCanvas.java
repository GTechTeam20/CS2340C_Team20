package com.example.dungeongame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.dungeongame.model.behaviors.Drawable;

import java.util.ArrayList;
import java.util.Comparator;

public class GameCanvas extends View {

    ArrayList<Drawable> drawables = new ArrayList<>();
    public GameCanvas(Context context) {
        super(context);
    }
    public GameCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawables.sort(Comparator.comparingInt(o -> o.layer));
        if (drawables != null) {
            for (Drawable d: drawables) {
                d.draw(canvas);
            }
        }
    }

    public ArrayList<Drawable> getDrawables() {
        return drawables;
    }
}
