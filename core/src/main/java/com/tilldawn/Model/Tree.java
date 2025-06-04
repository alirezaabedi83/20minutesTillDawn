package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.CollisionRect;

public class Tree extends Enemy {
    public Tree( float x,float y) {
        super(x,y,25000,0,new Texture(Gdx.files.internal("Images/Texture2D/T/T_TreeMonsterWalking.png")));
    }

    @Override
    public void update(float deltaTime, Player playerPos) {

    }


}
