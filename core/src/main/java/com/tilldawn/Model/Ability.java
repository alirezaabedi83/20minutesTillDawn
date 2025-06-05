package com.tilldawn.Model;

import static com.tilldawn.Model.AbilityType.*;

public class Ability {
    private AbilityType type;
    private boolean enabled;
    private float duration;
    private float timeLeft;

    public Ability(AbilityType type) {
        this.type = type;
        this.enabled = false;
        this.duration = 0;
        this.timeLeft = 0;
    }

    public void activate(Player player) {
        this.enabled = true;

        switch (type) {
            case VITALITY:
                player.setPlayerHealth(player.getPlayerHealth() + 1);
                break;
            case DAMAGER:
                duration = 10f;
                timeLeft = duration;
                player.getWeapon().setDamage((int)(player.getWeapon().getDamage() * 1.25));
                break;
            case PROCREASE:
                player.getWeapon().setProjectile(player.getWeapon().getProjectile() + 1);
                break;
            case AMOCREASE:
                player.getWeapon().setMaxAmmo(player.getWeapon().getMaxAmmo() + 5);
                break;
            case SPEEDY:
                duration = 10f;
                timeLeft = duration;
                player.setSpeed(player.getSpeed() * 2);
                break;
        }
    }

    public void update(float delta, Player player) {
        if (!enabled || duration == 0) return;
        timeLeft -= delta;
        if (timeLeft <= 0) {
            if (type == DAMAGER) {
                player.getWeapon().setDamage((int)(player.getWeapon().getDamage() / 1.25));
            } else if (type == SPEEDY) {
                player.setSpeed(player.getSpeed() / 2);
            }
            enabled = false;
        }
    }

    public AbilityType getType() {
        return type;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
