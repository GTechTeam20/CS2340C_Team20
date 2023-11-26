package com.example.dungeongame.model.collisions;

import com.example.dungeongame.model.Player;
import com.example.dungeongame.model.Sword;
import com.example.dungeongame.model.behaviors.DrawableSprite;
import com.example.dungeongame.model.enemy.Enemy;

import java.util.ArrayList;

public class CollisionManager {
    private static CollisionManager instance;
    private ArrayList<CollisionBox> collisions;

    private CollisionManager() {
        collisions = new ArrayList<>();
    }

    public static CollisionManager getInstance() {
        if (instance == null) {
            instance = new CollisionManager();
        }
        return instance;
    }

    public void addCollision(CollisionBox c) {
        collisions.add(c);
    }

    public void removeCollision(CollisionBox c) {
        collisions.remove(c);
    }

    public CollisionType checkSwordCollisions(CollisionBox collider) {
        for (CollisionBox box : collisions) {
            if (box == collider) {
                continue;
            }
            if (collider.getX() < box.getX() + box.getWidth()
                    &&
                    collider.getX() + collider.getWidth() > box.getX()
                    &&
                    collider.getY() < box.getY() + box.getHeight()
                    &&
                    collider.getY() + collider.getHeight() > box.getY()) {
            }
        }
        return CollisionType.NONE;
    }

    public CollisionType checkFutureCollisions(DrawableSprite sprite, int newX, int newY) {
        CollisionBox collider = null;
        boolean enemyCollision = false;
        if (sprite instanceof Enemy) {
            collider = ((Enemy) sprite).getCollisionBox();
        } else if (sprite instanceof Player) {
            collider = ((Player) sprite).getCollisionBox();
        }

        if (collider == null) {
            return CollisionType.NONE;
        }

        for (CollisionBox box : collisions) {
            if (box == collider) {
                continue;
            }
            if (newX < box.getX() + box.getWidth()
                    &&
                    newX + collider.getWidth() > box.getX()
                    &&
                    newY < box.getY() + box.getHeight()
                    &&
                    newY + collider.getHeight() > box.getY()) {
                if (box.getType() == CollisionType.ENEMY) {
                    enemyCollision = true;
                } else {
                    return box.getType();
                }
            }
        }

        // Move the sprite only if there is no wall collision
        if (sprite instanceof Enemy) {
            ((Enemy) sprite).updatePosition();
        } else if (sprite instanceof Player) {
            ((Player) sprite).updatePosition(newX, newY);
        }
        if (enemyCollision) {
            return CollisionType.ENEMY;
        }
        return CollisionType.NONE;
    }

}
