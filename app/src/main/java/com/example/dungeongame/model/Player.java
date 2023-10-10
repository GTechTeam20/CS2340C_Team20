package com.example.dungeongame.model;

// Layout for the singleton user class
public class Player {
    private Player() {}

    private static Player instance = null;

    public static Player getInstance() {

        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }
}
