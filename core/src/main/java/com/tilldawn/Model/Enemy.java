package com.tilldawn.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy {
    protected float health;
    protected float speed;
    protected Vector2 position;
    protected Vector2 velocity;
    protected Sprite sprite;
    protected CollisionRect rect;
    protected float spawnTime;

    public Enemy(float x, float y, float health, float speed) {
        this.position = new Vector2(x, y);
        this.health = health;
        this.speed = speed;
        this.velocity = new Vector2();
        this.rect = new CollisionRect(x, y, 32, 32);
    }

    public abstract void update(float delta, Player player);

    public boolean takeDamage(float damage) {
        health -= damage;
        return health <= 0;
    }

    public void moveTowards(Vector2 target, float delta) {
        velocity.set(target).sub(position).nor().scl(speed * delta);
        position.add(velocity);
        rect.move(position.x, position.y);
        sprite.setPosition(position.x, position.y);
    }

    public boolean collidesWith(CollisionRect other) {
        return rect.collidesWith(other);
    }

    // Getters and Setters
    public float getHealth() { return health; }
    public Sprite getSprite() { return sprite; }
    public Vector2 getPosition() { return position; }
    public CollisionRect getRect() { return rect; }
}