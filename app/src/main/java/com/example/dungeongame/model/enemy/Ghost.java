package com.example.dungeongame.model.enemy;

public class Ghost implements Enemy {
    private int damage = 2;
    private String sprite = "Ghost";
    private int movementSpeed = 3;
    private int x;
    private int y;
    @Override
    public void attackPlayer(){
        //attack the player
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
