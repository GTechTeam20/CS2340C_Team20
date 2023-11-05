package com.example.dungeongame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameCanvas extends View {

    public GameCanvas(Context context) {
        super(context);
    }
    public GameCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 500, 400, new Paint(Color.RED));
    }
}
