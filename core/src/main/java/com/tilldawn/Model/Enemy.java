package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Player;

// entities/Enemy.java
public abstract class Enemy {
    protected float x, y;
    protected int hp;
    protected float speed;
    protected Sprite sprite;
    protected CollisionRect rect;

    public Enemy(float x, float y, int hp, float speed, Texture texture) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.speed = speed;
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        sprite.setSize(64, 64);
        rect = new CollisionRect(x, y, 64, 64);
    }

    public abstract void update(float delta, Player player);

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public CollisionRect getRect() {
        return rect;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
