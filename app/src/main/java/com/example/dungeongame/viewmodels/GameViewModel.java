package com.example.dungeongame.viewmodels;

import android.view.KeyEvent;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.behaviors.Drawable;
import com.example.dungeongame.model.rooms.DungeonRoom;
import com.example.dungeongame.model.rooms.Room1;

import java.util.ArrayList;

public class GameViewModel {
    int currentRoom;
    DungeonRoom roomObject;
    int score;
    ArrayList<Drawable> drawables;

    public GameViewModel(ArrayList<Drawable> drawables) {
        this.drawables = drawables;

        score = 0;

        // Player
        drawables.add(Player.getInstance());

        // DungeonRoom
        setRoom(1);
    }

    public void getInput(int keyCode, KeyEvent event) {
        int newX = 0;
        int newY = 0;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                newX -= 20;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                newX += 20;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                newY -= 20;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                newY += 20;
                break;
            default:
                break;
        }
        if (Player.getInstance().attemptMove(newX, newY, 0)) {
            // move to next room
            setRoom(currentRoom + 1);
            score += 100;
        }
    }

    private void setRoom(int newRoom) {
        currentRoom = newRoom;
        clearRoom();
        roomObject = new Room1();

    }
    public void clearRoom() {
        if (roomObject != null) {
            roomObject.deleteRoom();
        }
    }
    public boolean GameFinished() {
        return currentRoom == 3;
    }
    public int getScore() {
        return score;
    }
}
