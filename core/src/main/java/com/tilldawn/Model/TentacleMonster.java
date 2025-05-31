package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class TentacleMonster extends Enemy {
    public TentacleMonster(float x, float y) {
        super(x, y, 25, 50);
        this.sprite = new Sprite(GameAssetManager.getGameAssetManager().getSmgTexture());
        this.sprite.setSize(32, 32);
    }

    @Override
    public void update(float delta, Player player) {
        moveTowards(new Vector2(player.getPosX(), player.getPosY()), delta);
    }
}