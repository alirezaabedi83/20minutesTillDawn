package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int x;
    private int y;
    private Vector2 velocity = new Vector2();
    private int damage;
    private boolean active = true;

    public Bullet(int x, int y,Player player){
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        sprite.setX(player.getPosX());
        sprite.setY(player.getPosY());
//        collisionRect=new CollisionRect(x,y,sprite.getWidth(),sprite.getHeight());
    }

//    public void update(float delta) {
//        position.x += velocity.x * delta;
//        position.y += velocity.y * delta;
//        sprite.setPosition(position.x, position.y);
//
//        // Check if bullet is out of bounds
//        if (position.x < 0 || position.x > Gdx.graphics.getWidth() ||
//                position.y < 0 || position.y > Gdx.graphics.getHeight()) {
//            active = false;
//        }
//
//    }

    // Getters and Setters
    public Sprite getSprite() { return sprite; }
//    public Vector2 getPosition() { return position; }
    public int getDamage() { return damage; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}