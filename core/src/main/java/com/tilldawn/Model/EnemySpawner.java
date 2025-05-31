package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Model.Enemy;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.TentacleMonster;
import com.tilldawn.Model.Eyebat;

import java.util.Random;

public class EnemySpawner {
    private Array<Enemy> activeEnemies = new Array<>();
    private float spawnTimer = 0;
    private float gameTime = 0;
    private float spawnRate = 3.0f;
    private Random random = new Random();

    public void update(float delta, Player player) {
        gameTime += delta;
        spawnTimer += delta;

        // Increase spawn rate over time
        spawnRate = Math.max(0.5f, 3.0f - (gameTime / 60f));

        if (spawnTimer >= spawnRate) {
            spawnTimer = 0;
            spawnEnemy(player);
        }

        for (Enemy enemy : activeEnemies) {
            enemy.update(delta, player);

            if (enemy.collidesWith(player.getRect())) {
                player.takeDamage(10);
            }
        }

        for (int i = activeEnemies.size - 1; i >= 0; i--) {
            if (activeEnemies.get(i).getHealth() <= 0) {
                activeEnemies.removeIndex(i);
            }
        }
    }

    private void spawnEnemy(Player player) {
        float x, y;
        if (random.nextBoolean()) {
            x = random.nextBoolean() ? -50 : Gdx.graphics.getWidth() + 50;
            y = random.nextFloat() * Gdx.graphics.getHeight();
        } else {
            x = random.nextFloat() * Gdx.graphics.getWidth();
            y = random.nextBoolean() ? -50 : Gdx.graphics.getHeight() + 50;
        }

        Enemy enemy;
        if (random.nextFloat() < 0.7f) {
            enemy = new TentacleMonster(x, y);
        } else {
            enemy = new Eyebat(x, y);
        }

        activeEnemies.add(enemy);
    }

    public Array<Enemy> getActiveEnemies() {
        return activeEnemies;
    }

    public float getGameTime() {
        return gameTime;
    }
}