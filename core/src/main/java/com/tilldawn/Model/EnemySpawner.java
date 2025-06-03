package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Model.Enemy;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.TentacleMonster;
import com.tilldawn.Model.Eyebat;
import com.tilldawn.Control.GameController;
import java.util.Random;

public class EnemySpawner {
    private float gameTime = 0;
    private float spawnTimer = 0;
    private Array<Enemy> enemies = new Array<>();
    private boolean bossSpawned = false;

    public void update(float delta, Player player) {
        gameTime += delta;
        spawnTimer += delta;

        if (gameTime > 30 && spawnTimer >= 3) {
            spawnTimer = 0;
            enemies.add(new TentacleMonster(randomEdgeX(), randomEdgeY()));
        }

        if (gameTime > 30 && ((int)gameTime % 10 == 0)) {
            enemies.add(new Eyebat(randomEdgeX(), randomEdgeY()));
        }

        if (!bossSpawned && gameTime >= GameController.TOTAL_TIME / 2f) {
            enemies.add(new ElderBoss(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f));
            bossSpawned = true;
        }

        for (int i = enemies.size - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            e.update(delta, player);
            System.out.println(e.hp);
            if(!e.isDead())   e.draw(Main.getBatch());
            if (e.isDead()) {
                DropManager.spawnDrop(e.getX(), e.getY());
                enemies.removeIndex(i);
            }
        }
    }

    private float randomEdgeX() {
        return MathUtils.randomBoolean() ? 0 : Gdx.graphics.getWidth();
    }

    private float randomEdgeY() {
        return MathUtils.randomBoolean() ? 0 : Gdx.graphics.getHeight();
    }

    public Array<Enemy> getEnemies() { return enemies; }
}
