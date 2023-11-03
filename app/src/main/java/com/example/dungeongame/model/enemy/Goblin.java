package com.example.dungeongame.model.enemy;

import java.util.Timer;
import java.util.TimerTask;

public class Goblin implements Enemy{
    private int damage = 4;
    private String sprite = "Goblin";
    private int movementSpeed = 3;
    private int x;
    private int y;

    private Timer timer;
    private TimerTask timerTask;

    public Goblin() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private int direction = 1;  // 1 for moving up, -1 for moving down

            @Override
            public void run() {
                move(direction);
                direction *= -1;  // Toggle direction between up and down
            }
        }, 0, 1000);  // Adjust the delay (1000ms = 1 second) as needed
    }
    @Override
    public void attackPlayer(){
        //attack the player
    }

    private void moveHelper(int direction) {
        y += movementSpeed * direction;
    }
    @Override
    public void move(int direction) {
        x += movementSpeed * direction;

        //while ()
        //if (Direction.equals("Left")) {
        //    x = x - movementSpeed;
        //}

        //if (Direction.equals("Right")) {
        //    x = x + movementSpeed;
        //}

        //if (Direction.equals("Up")) {
        //    y = y + movementSpeed;
        //}

        //if (Direction.equals("Down")) {
        //    y = y - movementSpeed;
        //}
    }

    public static Goblin createGoblin() {
        return new Goblin();
    }
}
