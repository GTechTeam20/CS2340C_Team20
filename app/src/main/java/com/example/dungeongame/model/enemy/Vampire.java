package com.example.dungeongame.model.enemy;

import android.widget.ImageView;

public class Vampire implements Enemy {
    private int damage = 2;
    private String sprite = "Vampire";
    private int movementSpeed = 3;
    private int x;
    private int y;

    private ImageView vampireImage;
    @Override
    public void attackPlayer(){
        //attack the player
    }

    public Vampire(int x, int y, ImageView vampireImage) {
        this.x = x;
        this.y = y;
        this.vampireImage = vampireImage;
        vampireImage.setX(x);
        vampireImage.setY(y);
    }
    @Override
    public void move(String Direction) {
        if (Direction.equals("Left")) {
            x = x - movementSpeed;
        }

        if (Direction.equals("Right")) {
            x = x + movementSpeed;
        }

        if (Direction.equals("Up")) {
            y = y + movementSpeed;
        }

        if (Direction.equals("Down")) {
            y = y - movementSpeed;
        }
    }
}
