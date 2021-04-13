package com.game.util;

public class AABB {

    public Vector2f position;
    public float width, height;
    public double size;

    public AABB(Vector2f position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;

        size = Math.max(width, height);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
