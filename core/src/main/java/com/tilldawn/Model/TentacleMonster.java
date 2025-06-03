package com.tilldawn.Model;

import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.Enemy;
import com.tilldawn.Model.Player;

public class TentacleMonster extends Enemy {
    public TentacleMonster(float x, float y) {
        super(x, y, 25, 60, GameAssetManager.getGameAssetManager().getTentacleMonster());
    }

    @Override
    public void update(float delta, Player player) {
        Vector2 dir = new Vector2(player.getPosX() - x, player.getPosY() - y).nor();
        x += dir.x * speed * delta;
        y += dir.y * speed * delta;
        sprite.setPosition(x, y);
        rect.move(x, y);
    }
}
