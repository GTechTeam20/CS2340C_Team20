package com.example.dungeongame.model;

import com.example.dungeongame.model.behaviors.InputObserver;

// Layout for the singleton user class
public class Player implements InputObserver {
    private int playerX;
    private int playerY;
    private Sprite sprite;
    private int[][] startingPosition = {
            {100, 700},
            {100, 700},
            {100, 700}};


    private Player() {
        playerX = 0;
        playerY = 0;
    }
    private static Player instance = null;

    public static Player getInstance() {

        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    @Override
    public boolean attemptMove(int x, int y, int currentRoom) {
        int newX = playerX + x;
        int newY = playerY + y;
        int collision = checkCollisions(newX, newY, currentRoom);
        if (collision == 2) {
            return true;
        }
        if (collision == 1) {
            playerX = newX;
            playerY = newY;
            if (sprite != null) {
                sprite.updateSpritePosition(playerX, playerY);
            }
        }
        return false;
    }

    public int checkCollisions(int x, int y, int currentRoom) {
        // wall = 0, open = 1, exit = 2
        if (currentRoom == 0) {
            if ((x < 500 && x > -400) && (y > 400 && y < 800)) {
                return 1;
            }
            if (y > 500 && y < 700 && x <= -400) {
                return 2;
            }
            return 0;
        }
        if (currentRoom == 1) {
            if ((x < 500 && x > -400) && (y > 400 && y < 800)) {
                return 1;
            }
            if (x > -100 && x < 100) {
                return 2;
            }
            return 0;
        }
        if (currentRoom == 2) {
            if ((x < 500 && x > -400) && (y > 450 && y < 800)) {
                return 1;
            }
            if (y > 500 && y < 700 && x <= -400) {
                return 2;
            }
            return 0;
        }
        return 0;
    }
    public void resetPosition(int room) {
        playerX = startingPosition[room][0];
        playerY = startingPosition[room][1];
        if (sprite != null) {
            sprite.updateSpritePosition(playerX, playerY);
        }
    }

    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
}
