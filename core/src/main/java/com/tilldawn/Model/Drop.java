package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop {
    private static final Texture texture = new Texture(Gdx.files.internal("Images/Texture2D/T/T_UISmallPanel.png"));
    private final Sprite sprite;
    private final CollisionRect rect;

    public Drop(float x, float y) {
        sprite = new Sprite(texture);
        sprite.setSize(32, 32);
        sprite.setPosition(x, y);
        rect = new CollisionRect(x, y, 32, 32);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update(Player player) {
        rect.move(sprite.getX(), sprite.getY());
    }

    public boolean collidesWithPlayer(Player player) {
        return rect.collidesWith(player.getRect());
    }

    public void apply(Player player) {
        player.increaseXp(3);
    }

    public CollisionRect getRect() {
        return rect;
    }
}
