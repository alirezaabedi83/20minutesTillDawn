package com.tilldawn.Model;

import com.badlogic.gdx.math.Rectangle;

public class CollisionRect {
    float x, y;
    float width, height;

    public CollisionRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(CollisionRect rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height &&
                x + width > rect.x && y + height > rect.y;
    }

    // Getters
    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
}