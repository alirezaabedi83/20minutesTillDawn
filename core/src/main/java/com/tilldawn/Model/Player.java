package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    private Texture playerTexture;
    private Sprite playerSprite;
    private Weapon weapon;
    private float posX = 0;
    private float posY = 0;
    private int xp;
    private int level=1;
    private Boolean isInvincible=false;
    private int kills;

    private int playerHealth = 100;
    private CollisionRect rect;
    private float time = 0;
    private float speed = 50;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;
    private float invincibleTimeLeft;
    private boolean isDamaged;

    public Player(String heroName) {
        switch (heroName) {
            case "SHANA":
                this.speed = 4;
                this.playerHealth = 4;
                playerTexture = new Texture("Images/Sprite/Idle/Idle_4 #8316.png");
                break;
            case "DIAMOND":
                this.speed = 1;
                this.playerHealth = 7;
                playerTexture = new Texture("Images/Sprite/Idle/Idle_0 #8326.png");
                break;
            case "SCARLET":
                this.speed = 5;
                this.playerHealth = 3;
                playerTexture = new Texture("Images/Sprite/Idle/Idle_0 #8329.png");
                break;
            case "LILITH":
                this.speed = 3;
                this.playerHealth = 5;
                playerTexture = new Texture("Images/Sprite/Idle/Idle_0 #8333.png");
                break;
            case "DASHER":
                this.speed = 10;
                this.playerHealth = 2;
                playerTexture = new Texture("Images/Sprite/Idle/Idle_5 #8302.png");
                break;
            default:
                this.speed = 50;
                this.playerHealth = 100;
                playerTexture = new Texture("Images/Sprite/Idle/Idle_4 #8316.png");
        }
        playerSprite = new Sprite(playerTexture);
        posX= (float) Gdx.graphics.getWidth() / 2;
        posY=(float) Gdx.graphics.getHeight() / 2;
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2,
                playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
    }

    public void takeDamage(int damage) {
        if (!isInvincible || invincibleTimeLeft <= 0) {
            this.playerHealth -= damage;
            isInvincible = true;
            invincibleTimeLeft = 1.0f;
            this.isDamaged = true;
        }
    }

    public void updateInvincibility(float delta) {
        if (isInvincible) {
            invincibleTimeLeft -= delta;
            if (invincibleTimeLeft <= 0) {
                isInvincible = false;
                invincibleTimeLeft = 0;
            }
        }
    }


    // Character methods
    public boolean increaseXp(int amount) {
        xp += amount;
        if (xp >= maxLevelXp() + xpToNextLevel(level)) {
            level++;
//            SFX.LEVEL.play();
            return true;
        }
        return false;
    }

    public int xpToNextLevel(int level) {
        return level * 20;
    }

    public int maxLevelXp() {
        switch (level) {
            case 1: return 0;
            case 2: return 20;
            case 3: return 60;
            case 4: return 120;
            case 5: return 200;
            case 6: return 300;
            case 7: return 420;
            case 8: return 560;
            case 9: return 720;
            case 10: return 900;
            case 11: return 1100;
            case 12: return 1320;
            case 13: return 1560;
            case 14: return 1820;
            default: return 2000;
        }
    }

    public void increaseKills() {
        kills++;
    }

    // Getters and Setters
    public Texture getPlayerTexture() { return playerTexture; }
    public void setPlayerTexture(Texture playerTexture) { this.playerTexture = playerTexture; }
    public Sprite getPlayerSprite() { return playerSprite; }
    public void setPlayerSprite(Sprite playerSprite) { this.playerSprite = playerSprite; }
    public float getPosX() { return posX; }
    public void setPosX(float posX) { this.posX = posX; }
    public float getPosY() { return posY; }
    public void setPosY(float posY) { this.posY = posY; }
    public int getPlayerHealth() { return playerHealth; }
    public void setPlayerHealth(int playerHealth) { this.playerHealth = playerHealth; }
    public CollisionRect getRect() { return rect; }
    public void setRect(CollisionRect rect) { this.rect = rect; }
    public boolean isPlayerIdle() { return isPlayerIdle; }
    public void setPlayerIdle(boolean playerIdle) { isPlayerIdle = playerIdle; }
    public boolean isPlayerRunning() { return isPlayerRunning; }
    public void setPlayerRunning(boolean playerRunning) { isPlayerRunning = playerRunning; }
    public float getTime() { return time; }
    public void setTime(float time) { this.time = time; }
    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Boolean getInvincible() {
        return isInvincible;
    }

    public void setInvincible(Boolean invincible) {
        isInvincible = invincible;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public float getInvincibleTimeLeft() {
        return invincibleTimeLeft;
    }

    public void setInvincibleTimeLeft(float invincibleTimeLeft) {
        this.invincibleTimeLeft = invincibleTimeLeft;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }
}