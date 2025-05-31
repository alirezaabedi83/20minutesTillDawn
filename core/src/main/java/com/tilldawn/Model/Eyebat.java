package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Eyebat extends Enemy {
    private float shootTimer = 0;
    private static final float SHOOT_INTERVAL = 3.0f;

    public Eyebat(float x, float y) {
        super(x, y, 50, 40);
        this.sprite = new Sprite(GameAssetManager.getGameAssetManager().getSmgTexture());
        this.sprite.setSize(32, 32);
    }

    @Override
    public void update(float delta, Player player) {
        moveTowards(new Vector2(player.getPosX(), player.getPosY()), delta);

        shootTimer += delta;
        if (shootTimer >= SHOOT_INTERVAL) {
            shootTimer = 0;
            // TODO: Implement shooting projectile at player
        }
    }
}