package com.example.dungeongame.model.collisions;

public class CollisionBox {
    int x;
    int y;
    int width;
    int height;
    CollisionType type;
    public CollisionBox(int x, int y, int width, int height, CollisionType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public void delete() {
        CollisionManager.getInstance();
    }

    public void updatePostion(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
