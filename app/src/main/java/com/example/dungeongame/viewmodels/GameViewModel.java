package com.example.dungeongame.viewmodels;

import android.renderscript.ScriptGroup;
import android.view.KeyEvent;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.behaviors.Drawable;
import com.example.dungeongame.model.behaviors.InputObserver;
import com.example.dungeongame.views.GameCanvas;

import java.util.ArrayList;

public class GameViewModel {
    int currentRoom;
    int score;
    ArrayList<Drawable> drawables;

    public GameViewModel(ArrayList<Drawable> drawables) {
        this.drawables = drawables;

        currentRoom = 0;
        score = 0;
        // Initialize Game Objects
        drawables.add(Player.getInstance());
        // Player
        // DungeonRoom
    }

    public void getInput(int keyCode, KeyEvent event) {
        return;
    }
    public int GameFinished() {
        return -1;
    }
}
