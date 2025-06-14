package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.GameSettings;
import com.tilldawn.Model.Player;

public class PlayerController {
    public OrthographicCamera camera;
    private Player player;


    public PlayerController(Player player, OrthographicCamera camera){
        this.player = player;
        this.camera=camera;
    }

    public void update(float delta) {
        camera.position.set(player.getPosX() + player.getPlayerSprite().getWidth() / 2f,
                player.getPosY() + player.getPlayerSprite().getHeight() / 2f,
                0);
        camera.update();

        player.getPlayerSprite().draw(Main.getBatch());

        handlePlayerInput(delta);
        player.updateInvincibility(delta);

        if(player.isPlayerIdle()){
//            idleAnimation();
        }
    }

    private void updateInvincibility(float delta) {
        if (player.getInvincible()) {
            player.setInvincibleTimeLeft(player.getInvincibleTimeLeft()-delta);
            if (player.getInvincibleTimeLeft() <= 0) {
                player.setInvincible(false);
            }
        }
    }


    public void handlePlayerInput(float delta) {
        float moveX = 0, moveY = 0;

        String keyUp = GameSettings.getInstance().getKeyUp().toUpperCase();
        String keyDown = GameSettings.getInstance().getKeyDown().toUpperCase();
        String keyLeft = GameSettings.getInstance().getKeyLeft().toUpperCase();
        String keyRight = GameSettings.getInstance().getKeyRight().toUpperCase();

        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(keyUp))) moveY += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(keyDown))) moveY -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(keyLeft))) moveX -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.valueOf(keyRight))) moveX += 1;
        // Diagonal movement normalization
        if (moveX != 0 && moveY != 0) {
            float len = (float)Math.sqrt(moveX * moveX + moveY * moveY);
            moveX /= len;
            moveY /= len;
        }

        // Apply movement
        player.setPosX(player.getPosX() + moveX * player.getSpeed() * delta);
        player.setPosY(player.getPosY() + moveY * player.getSpeed() * delta);

        float playerX = player.getPosX();
        float playerY = player.getPosY();
        player.getPlayerSprite().setPosition(playerX, playerY);
        player.getWeapon().getWeaponSprite().setPosition(playerX,playerY);

        // Update collision rect
        player.getRect().move(player.getPosX(), player.getPosY());
    }




    private void levelUp() {
//        level++;
//        player.increaseXp(-experienceToNextLevel);
//        experienceToNextLevel = 20 * level + 40; // Formula for next level XP

        // TODO: Implement ability selection
    }


    public void idleAnimation(){
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getCharacter1_idle_animation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
