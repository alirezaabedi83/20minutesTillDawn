package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Eyebat extends Enemy {
    private float shootTimer = 0;

    public Eyebat(float x, float y) {
        super(x, y, 50, 50, GameAssetManager.getGameAssetManager().getEyebatMonster());
    }

    @Override
    public void update(float delta, Player player) {
        shootTimer += delta;
        Vector2 dir = new Vector2(player.getPosX() - x, player.getPosY() - y).nor();
        x += dir.x * speed * delta;
        y += dir.y * speed * delta;
        sprite.setPosition(x, y);
        rect.move(x, y);

        if (shootTimer >= 3f) {
            shootTimer = 0;
//            BulletManager.spawnEnemyBullet(x, y, player.getPosX(), player.getPosY());
        }
    }
}
