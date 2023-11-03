package com.example.dungeongame.model.enemy;

import android.media.Image;
import android.widget.ImageView;

public class Ghost implements Enemy {
    private int damage = 2;
    private String sprite = "Ghost";
    private int movementSpeed = 3;
    private int x;
    private int y;

    private ImageView ghostImage;
    @Override
    public void attackPlayer(){
        //attack the player
    }

    public Ghost(int x, int y, ImageView ghostImage) {
        this.x = x;
        this.y = y;
        this.ghostImage = ghostImage;
        ghostImage.setX(x);
        ghostImage.setY(y);
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
