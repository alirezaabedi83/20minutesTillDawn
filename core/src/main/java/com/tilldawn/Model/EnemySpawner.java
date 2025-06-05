package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.tilldawn.Main;
import com.tilldawn.Control.GameController;

public class EnemySpawner {
    private float gameTime = 0f;
    private float spawnCheckSimple = 0f;
    private float spawnCheckBat = 0f;
    private float bossTimer = 0f;
    private boolean treeSpawned = false;
    private boolean bossSpawned = false;
    private Array<Drop> drops = new Array<>();


    private Array<Enemy> enemies = new Array<>();
    private ElectricWall wall = new ElectricWall(50);

    public void update(float delta, Player player) {
        gameTime += delta;
        spawnCheckSimple += delta;
        spawnCheckBat += delta;

        if (!treeSpawned) {
            treeSpawned = true;
            for (int i = 0; i < 200; i++) {
                enemies.add(new Tree(randomAroundPlayer(player, 8000), randomAroundPlayer(player, 8000)));
            }
        }

        if (gameTime >= 30 && spawnCheckSimple >= 3) {
            spawnCheckSimple = 0;
            int count = (int) gameTime / 30;
            for (int i = 0; i < count; i++) {
                enemies.add(new TentacleMonster(randomAroundPlayer(player, 1500), randomAroundPlayer(player, 1500)));
            }
        }

        if (gameTime >= 4 && spawnCheckBat >= 10) {
            spawnCheckBat = 0;
            int count = (int) ((4 * gameTime - 30) / 30);
            for (int i = 0; i < count; i++) {
                enemies.add(new Eyebat(randomAroundPlayer(player, 1500), randomAroundPlayer(player, 1500)));
            }
        }

        if (!bossSpawned && gameTime >= Game.getInstance().getTotalGameTime() / 2f) {
            enemies.add(new ElderBoss(randomAroundPlayer(player, 1500), randomAroundPlayer(player, 1500)));
            bossSpawned = true;
            wall.setActive(true);
        }



        for (int i = enemies.size - 1; i >= 0; i--) {
            Enemy e = enemies.get(i);
            e.update(delta, player);
            if (!player.getInvincible() && e.getRect().collidesWith(player.getRect())) {
                player.takeDamage(1);
                e.setHp(-1);

            }

            if (!e.isDead()) {
                e.draw(Main.getBatch());
            } else {
                if (e.isDead()) {
                    if (e instanceof ElderBoss) {
                        wall.setActive(false);
                    }

                    drops.add(new Drop(e.getX(), e.getY()));
                    enemies.removeIndex(i);
                }

            }

            if (e instanceof ElderBoss && bossSpawned) {
                bossTimer += delta;
                if (bossTimer >= 5f) {
                    bossTimer = 0;
                    dashToPlayer(e, player);
                }
            }
        }


        for (Drop drop : drops) {
            drop.draw(Main.getBatch());
        }


        for (int i = drops.size - 1; i >= 0; i--) {
            Drop drop = drops.get(i);
            drop.update(player);
            if (drop.collidesWithPlayer(player)) {
                drop.apply(player);
                drops.removeIndex(i);
            }
        }

        wall.draw(Main.getBatch());
        if (wall.isActive() && wall.isCharacterTouchingWall(player)) {
            player.takeDamage(1);
        }

        if (wall.isActive()) wall.update(delta);
    }

    private float randomAroundPlayer(Player player, int range) {
        return MathUtils.random((int) player.getPosX() - range, (int) player.getPosX() + range);
    }

    private void dashToPlayer(Enemy enemy, Player player) {
        if (enemy.isDead()) {
            return;
        }
        float oldSpeed = enemy.getSpeed();
        enemy.setSpeed(oldSpeed * 10);
        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                enemy.setSpeed(oldSpeed);
            }
        }, 3);
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public ElectricWall getWall() {
        return wall;
    }
}
