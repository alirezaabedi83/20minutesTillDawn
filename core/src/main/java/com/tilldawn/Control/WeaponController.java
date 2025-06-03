package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.*;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private Weapon weapon;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private float reloadTimer = 0;
    private boolean isReloading = false;
    private Player player;
    private EnemySpawner enemySpawner;

    public WeaponController(Weapon weapon, Player player) {
        this.weapon = weapon;
        this.player = player;
    }

    public void setEnemySpawner(EnemySpawner enemySpawner) {
        this.enemySpawner = enemySpawner;
    }

    public void update(float delta) {
        weapon.getWeaponSprite().setPosition(player.getPosX(),player.getPosY());

        weapon.getWeaponSprite().draw(Main.getBatch());

        updateBullets(delta);

        if (isReloading) {
            reloadTimer -= delta;
            if (reloadTimer <= 0) {
                weapon.setAmmo(weapon.getMaxAmmo());
                isReloading = false;
            }
        }

        // Auto-reload when empty

    }




    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getWeaponSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int screenX, int screenY) {
        if (isReloading || weapon.getAmmo() <= 0) return;

//        for (int i = 0; i < 10; i++) {
            float delay = 1 * 0.1f;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    bullets.add(new Bullet(screenX, screenY, player));
//                SFX.SHOOT.play();
                }
            }, delay);
//        }


        weapon.setAmmo(weapon.getAmmo() - 1);

        if (weapon.getAmmo() <= 0 && !isReloading && GameSettings.getInstance().isAutoReload()) {
            startReload();
        }


    }

    public void startReload() {
        if (!isReloading && weapon.getAmmo() < weapon.getMaxAmmo()) {
            isReloading = true;
            reloadTimer = weapon.getReloadTime();
        }
    }

    private void updateBullets(float delta) {

            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Bullet b = iterator.next();
                if (!b.isActive()) {
                    iterator.remove();
                    continue;
                }
                b.getSprite().draw(Main.getBatch());
                Vector2 direction = new Vector2(
                        Gdx.graphics.getWidth() / 2f - b.getX(),
                        Gdx.graphics.getHeight() / 2f - b.getY()
                ).nor();
                b.getSprite().setX(b.getSprite().getX() - direction.x * 5);
                b.getSprite().setY(b.getSprite().getY() + direction.y * 5);
                b.getRect().move(b.getSprite().getX(),b.getSprite().getY());

                if (enemySpawner != null) {
                    for (Enemy enemy : enemySpawner.getEnemies()) {
                        if (b.getRect().collidesWith(enemy.getRect())) {
                            enemy.takeDamage(b.getDamage());
                            player.gainExperience(3);

                            iterator.remove();
                            break;
                        }
                    }
                }
            }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public float getReloadTimer() {
        return reloadTimer;
    }

    public void setReloadTimer(float reloadTimer) {
        this.reloadTimer = reloadTimer;
    }

    public boolean isReloading() {
        return isReloading;
    }

    public void setReloading(boolean reloading) {
        isReloading = reloading;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
