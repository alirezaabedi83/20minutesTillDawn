package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.Player;

public class ElderBoss extends Enemy {
    private float dashTimer = 0;
    private boolean shieldShrinking = true;
    private float shieldRadius = 600; // start full-screen

    public ElderBoss(float x, float y) {
        super(x, y, 400, 8, new Texture("Images/Texture2D/ElderBrain/ElderBrain.png"));
    }

    @Override
    public void update(float delta, Player player) {
        dashTimer += delta;
        if (dashTimer >= 5f) {
            dashTimer = 0;
            Vector2 dir = new Vector2(player.getPosX() - x, player.getPosY() - y).nor();
            x += dir.x * speed * 5f;
            y += dir.y * speed * 5f;
        } else {
            Vector2 dir = new Vector2(player.getPosX() - x, player.getPosY() - y).nor();
            x += dir.x * speed * delta;
            y += dir.y * speed * delta;
        }
        sprite.setPosition(x, y);
        rect.move(x, y);

        if (shieldShrinking) shieldRadius -= 10 * delta;
    }

    public float getShieldRadius() {
        return shieldRadius;
    }

    public boolean insideShield(Player player) {
        float dist = Vector2.dst(player.getPosX(), player.getPosY(), x, y);
        return dist < shieldRadius;
    }
}
