package com.example.dungeongame.model.enemy;

import android.widget.ImageView;

public class Mummy implements Enemy {
    private int damage = 2;
    private String sprite = "Mummy";
    private int movementSpeed = 3;
    private int x;
    private int y;

    private ImageView mummyImage;
    @Override
    public void attackPlayer(){
        //attack the player
    }

    public Mummy(int x, int y, ImageView mummyImage) {
        this.x = x;
        this.y = y;
        this.mummyImage = mummyImage;
        mummyImage.setX(x);
        mummyImage.setY(y);
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
