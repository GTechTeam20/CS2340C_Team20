package com.example.dungeongame.viewmodels;

import android.view.KeyEvent;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.Sword;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.collisions.CollisionBox;
import com.example.dungeongame.model.collisions.CollisionManager;
import com.example.dungeongame.model.enemy.Crab;
import com.example.dungeongame.model.enemy.Enemy;
import com.example.dungeongame.model.enemy.Ghost;
import com.example.dungeongame.model.enemy.Goblin;
import com.example.dungeongame.model.enemy.Zombie;
import com.example.dungeongame.model.powerups.Coin;
import com.example.dungeongame.model.powerups.Heart;
import com.example.dungeongame.model.powerups.Powerup;
import com.example.dungeongame.model.powerups.Speed;
import com.example.dungeongame.model.rooms.DungeonRoom;
import com.example.dungeongame.model.rooms.Room1;
import com.example.dungeongame.model.rooms.Room2;
import com.example.dungeongame.model.rooms.Room3;

import java.util.ArrayList;
import java.util.List;

public class GameViewModel {
    private int currentRoom;
    private DungeonRoom roomObject;
    private int score;
    private ArrayList<DrawableSprite> drawables;

    // Represent currently active enemies
    private List<DrawableSprite> enemies;
    private List<DrawableSprite> powerups;

    public GameViewModel(ArrayList<DrawableSprite> drawables) {
        this.drawables = drawables;
        this.enemies = new ArrayList<>();
        this.powerups = new ArrayList<>();
        score = 0;

        // Player
        drawables.add(Player.getInstance());
        Sword sword = new Sword(this);
        Player.getInstance().setSword(sword);
        this.drawables.add(sword);

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
        case KeyEvent.KEYCODE_SPACE:
            Player.getInstance().swingSword();
            return;
        default:
            break;
        }
        if (Player.getInstance().attemptMove(newX, newY, 0)) {
            // move to next room
            setRoom(currentRoom + 1);
            if (Player.getInstance().getDifficultyLevel().equals("Easy")) {
                score += 50;
            } else if (Player.getInstance().getDifficultyLevel().equals("Medium")) {
                score += 100;
            } else if (Player.getInstance().getDifficultyLevel().equals("Hard")) {
                score += 150;
            }
        }
    }

    public void destroyEnemy(CollisionBox enemyCollider) {
        for (DrawableSprite enemy: enemies) {
            if (((Enemy) enemy).getCollisionBox() == enemyCollider) {
                drawables.remove(enemy);
                CollisionManager.getInstance().removeCollision(((Enemy) enemy).getCollisionBox());
                enemies.remove(enemy);
            }
        }

        score += 30;
    }

    private void setRoom(int newRoom) {
        currentRoom = newRoom;
        clearRoom();
        if (newRoom == 1) {
            roomObject = new Room1();
            drawables.add(roomObject);
            Heart heart = Heart.getInstance(333, 332, "heart");
            powerups.add(heart);
            // Enemies
            Ghost ghostEnemy = Ghost.getInstance(200, 300, "ghost", 100, 700, 100, 500);
            Goblin goblinEnemy = Goblin.getInstance(400, 600, "goblin", 200, 800, 200, 600);
            enemies.add(ghostEnemy);
            enemies.add(goblinEnemy);
        }
        if (newRoom == 2) {
            roomObject = new Room2();
            drawables.add(roomObject);

            Speed speed = Speed.getInstance(333, 800, "speed");
            powerups.add(speed);
            Ghost ghostEnemy = Ghost.getInstance(200, 300, "ghost", 100, 700, 100, 500);
            Zombie zombieEnemy = Zombie.getInstance(600, 900, "zombie", 300, 900, 300, 700);
            enemies.add(ghostEnemy);
            enemies.add(zombieEnemy);
        }
        if (newRoom == 3) {
            roomObject = new Room3();
            drawables.add(roomObject);
            Coin coin = Coin.getInstance(333, 500, "coin");
            powerups.add(coin);
            // Enemies
            Crab crabEnemy = Crab.getInstance(200, 300, "crab", 100, 700, 100, 500);
            Zombie zombieEnemy = Zombie.getInstance(600, 900, "zombie", 300, 900, 300, 700);
            enemies.add(crabEnemy);
            enemies.add(zombieEnemy);
        }
        if (newRoom == 4) {
            score += 200;
        }
        for (DrawableSprite enemy: enemies) {
            drawables.add(enemy);
            CollisionManager.getInstance().addCollision(((Enemy) enemy).getCollisionBox());
        }
        for (DrawableSprite powerup: powerups) {
            drawables.add(powerup);
            CollisionManager.getInstance().addCollision(((Powerup) powerup).getCollisionBox());
        }
    }
    public void clearRoom() {
        if (roomObject != null) {
            drawables.remove(roomObject);
            for (DrawableSprite enemy: enemies) {
                drawables.remove(enemy);
                CollisionManager.getInstance().removeCollision(((Enemy) enemy).getCollisionBox());
            }
            for (DrawableSprite powerup: powerups) {
                drawables.remove(powerup);
                CollisionManager.getInstance().removeCollision(((Powerup) powerup).getCollisionBox());
            }
            enemies.clear();
            powerups.clear();
            roomObject.deleteRoom();
        }
    }
    public boolean gameFinished() {
        return currentRoom >= 4;
    }
    public int getScore() {
        return score + Player.getInstance().getPlayerHealth();
    }
}
