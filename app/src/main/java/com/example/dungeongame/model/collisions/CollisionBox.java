package com.example.dungeongame.model.collisions;

public class CollisionBox {
    private int x;
    private int y;
    private int width;
    private int height;
    private CollisionType type;
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

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public CollisionType getType() {
        return type;
    }
}
