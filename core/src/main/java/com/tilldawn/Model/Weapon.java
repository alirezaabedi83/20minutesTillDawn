package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private Sprite weaponSprite;
    private int ammo;
    private int maxAmmo;
    private float reloadTime;
    private int damage;
    private int projectile;
    private String type;

    public Weapon(String type) {
        this.type = type;
        switch (type) {
            case "Revolver":
                this.maxAmmo = 6;
                this.reloadTime = 1.5f;
                this.damage = 20;
                this.projectile=1;
                this.weaponSprite = new Sprite(GameAssetManager.getGameAssetManager().getRevolverTexture());

                break;
            case "Shotgun":
                this.maxAmmo = 2;
                this.reloadTime = 1.5f;
                this.damage = 10;
                this.projectile=4;
                this.weaponSprite = new Sprite(GameAssetManager.getGameAssetManager().getShotgun());

                break;
            case "SMG":
                this.maxAmmo = 24;
                this.reloadTime = 2.5f;
                this.damage = 8;
                this.projectile=1;
                this.weaponSprite = new Sprite(GameAssetManager.getGameAssetManager().getSmgTexture());

                break;
            default:
                this.maxAmmo = 6;
                this.reloadTime = 1.5f;
                this.damage = 20;
                this.projectile=1;
                this.weaponSprite = new Sprite(GameAssetManager.getGameAssetManager().getSmgTexture());

        }
        this.ammo = maxAmmo;
        this.weaponSprite.setSize(100, 100);
        weaponSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
    }

    // Getters and Setters
    public Sprite getWeaponSprite() { return weaponSprite; }
    public int getAmmo() { return ammo; }
    public void setAmmo(int ammo) { this.ammo = ammo; }
    public int getMaxAmmo() { return maxAmmo; }
    public float getReloadTime() { return reloadTime; }
    public int getDamage() { return damage; }
    public String getType() { return type; }

    public void setWeaponSprite(Sprite weaponSprite) {
        this.weaponSprite = weaponSprite;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public void setReloadTime(float reloadTime) {
        this.reloadTime = reloadTime;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProjectile() {
        return projectile;
    }

    public void setProjectile(int projectile) {
        this.projectile = projectile;
    }
}