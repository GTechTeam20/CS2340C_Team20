package com.example.dungeongame.model.enemy;

import android.widget.ImageView;

public class Goblin implements Enemy {
    private int damage = 2;
    private String sprite = "Goblin";
    private int movementSpeed = 3;
    private int x;
    private int y;

    private ImageView goblinImage;
    @Override
    public void attackPlayer(){
        //attack the player
    }

    public Goblin(int x, int y, ImageView goblinImage) {
        this.x = x;
        this.y = y;
        this.goblinImage = goblinImage;
        goblinImage.setX(x);
        goblinImage.setY(y);
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
