package com.example.dungeongame.model;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class Sprite {
    private int x, y;
    private ImageView SpriteImage;

    public Sprite(int x, int y, ImageView SpriteImage) {
        this.x = x;
        this.y = y;
        this.SpriteImage = SpriteImage;
    }

    public ImageView getCharacterImage() {
        return SpriteImage;
    }

    public void setCharacterImage(ImageView SpriteImage) {
        this.SpriteImage = SpriteImage;
    }
    public void updateSpritePosition(int x, int y) {
        this.x = x;
        this.y = y;

    }
}
