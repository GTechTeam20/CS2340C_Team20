package com.example.dungeongame.model.behaviors;

public interface InputObserver {
    public abstract boolean attemptMove(int x, int y, int currentRoom);
}
