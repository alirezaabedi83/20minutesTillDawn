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
    private float playerHealth = 100;
    private CollisionRect rect;
    private float time = 0;
    private float speed = 50;
    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

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

    // Getters and Setters
    public Texture getPlayerTexture() { return playerTexture; }
    public void setPlayerTexture(Texture playerTexture) { this.playerTexture = playerTexture; }
    public Sprite getPlayerSprite() { return playerSprite; }
    public void setPlayerSprite(Sprite playerSprite) { this.playerSprite = playerSprite; }
    public float getPosX() { return posX; }
    public void setPosX(float posX) { this.posX = posX; }
    public float getPosY() { return posY; }
    public void setPosY(float posY) { this.posY = posY; }
    public float getPlayerHealth() { return playerHealth; }
    public void setPlayerHealth(float playerHealth) { this.playerHealth = playerHealth; }
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

    public void takeDamage(int i) {
        this.playerHealth-=i;
    }

    public void gainExperience(int i) {

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}