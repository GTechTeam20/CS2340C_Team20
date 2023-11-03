package com.example.dungeongame.model.enemy;

interface Enemy {
    abstract void attackPlayer();
    abstract void move(int direction);
}
