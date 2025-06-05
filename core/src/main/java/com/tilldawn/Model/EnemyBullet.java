package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;

public class EnemyBullet {
    private Sprite sprite;
    private Vector2 velocity;
    private float speed = 30f;
    private CollisionRect rect;
    private boolean active = true;

    public EnemyBullet(float startX, float startY, float targetX, float targetY) {
        sprite = new Sprite(new Texture(GameAssetManager.getGameAssetManager().getBullet()));
        sprite.setPosition(startX, startY);
        sprite.setSize(20, 20);

        Vector2 direction = new Vector2(targetX - startX, targetY - startY).nor();
        velocity = direction.scl(speed);

        rect = new CollisionRect(startX, startY, 20, 20);
    }

    public void update(float delta) {
        if (!active) return;
        sprite.setX(sprite.getX() + velocity.x * delta);
        sprite.setY(sprite.getY() + velocity.y * delta);
        rect.move(sprite.getX(), sprite.getY());
    }

    public void draw() {
        if (active) sprite.draw(Main.getBatch());
    }

    public boolean collidesWith(Player player) {
        return rect.collidesWith(player.getRect());
    }

    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }
}
