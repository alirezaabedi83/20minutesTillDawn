package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
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
    private float time=0;
    private boolean dead = false;




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
        if (hp <= 0) {
            dead = true;
            time = 0;
        }
    }

    public void updateDeadAnimation(float delta) {
        if (dead && !GameAssetManager.getGameAssetManager().getDeadAnimation().isAnimationFinished(time)) {
            time += delta;
            sprite.setRegion(GameAssetManager.getGameAssetManager().getDeadAnimation().getKeyFrame(time));
        }
    }



    public void idleAnimation(Enemy enemy,float delta){
        enemy.getSprite().setRegion(GameAssetManager.getGameAssetManager().getDeadAnimation().getKeyFrame(enemy.getTime()));

        if (!GameAssetManager.getGameAssetManager().getDeadAnimation().isAnimationFinished(enemy.getTime())) {
            enemy.setTime(enemy.getTime()+delta);
        }
        else {
            enemy.setTime(0);
        }

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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
