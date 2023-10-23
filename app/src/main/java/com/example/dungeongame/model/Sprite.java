package com.example.dungeongame.model;
import android.widget.ImageView;

public class Sprite {
    private int x;
    private int y;
    private ImageView spriteImage;

    public Sprite(int x, int y, ImageView spriteImage) {
        this.x = x;
        this.y = y;
        this.spriteImage = spriteImage;
        spriteImage.setX(x);
        spriteImage.setY(y);
    }

    public ImageView getCharacterImage() {
        return spriteImage;
    }

    public void setCharacterImage(ImageView spriteImage) {
        this.spriteImage = spriteImage;
    }
    public void updateSpritePosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.spriteImage.setX(x);
        this.spriteImage.setY(y);
    }
}
