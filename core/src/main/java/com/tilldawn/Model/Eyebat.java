package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.*;

public class Eyebat extends Enemy {
    private float shootTimer = 0f;
    private static Array<EnemyBullet> bullets = new Array<>();


    public Eyebat(float x, float y) {
        super(x, y, 50, 50, GameAssetManager.getGameAssetManager().getEyebatMonster());
    }

    @Override
    public void update(float delta, Player player) {

        shootTimer += delta;
        if (shootTimer >= 3f) {
            shootTimer = 0f;
            bullets.add(new EnemyBullet(x, y, player.getPosX(), player.getPosY()));
        }

        Vector2 dir = new Vector2(player.getPosX() - x, player.getPosY() - y).nor();
        x += dir.x * speed * delta;
        y += dir.y * speed * delta;
        sprite.setPosition(x, y);
        rect.move(x, y);

        for (int i = bullets.size - 1; i >= 0; i--) {
            EnemyBullet b = bullets.get(i);
            b.update(delta);
            b.draw();

            if (b.collidesWith(player) && !player.getInvincible()) {
                player.takeDamage(1,delta);
                player.setInvincible(true);
                player.setInvincibleTimeLeft(1f);
                b.deactivate();
            }

            if (!b.isActive()) bullets.removeIndex(i);
        }

    }
}
