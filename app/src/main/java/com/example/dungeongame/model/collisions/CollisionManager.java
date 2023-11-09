package com.example.dungeongame.model.collisions;

import java.util.ArrayList;

public class CollisionManager {
    private static CollisionManager instance;
    private ArrayList<CollisionBox> collisions;

    public CollisionManager() {
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

    public CollisionType checkCollisions(CollisionBox collider) {
        for (CollisionBox box : collisions) {
            if (box == collider) {
                continue;
            }
            if (collider.x < box.x + box.width &&
                    collider.x + collider.width > box.x &&
                    collider.y < box.y + box.height &&
                    collider.y + collider.height > box.y) {
                return box.type;
            };
        }
        return CollisionType.NONE;
    }

    public CollisionType checkFutureCollisions(CollisionBox collider, int newX, int newY) {
        for (CollisionBox box : collisions) {
            if (box == collider) {
                continue;
            }
            if (newX < box.x + box.width &&
                    newX + collider.width > box.x &&
                    newY < box.y + box.height &&
                    newY + collider.height > box.y) {
                return box.type;
            }
        }
        return CollisionType.NONE;
    }
}
