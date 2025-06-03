package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Main;

public class WorldController {
    private PlayerController playerController;
    private OrthographicCamera camera;
    private Texture backgroundTexture;
    private float backgroundX = 0;
    private float backgroundY = 0;

    public WorldController(OrthographicCamera camera) {
        this.backgroundTexture = new Texture("background.png");
        this.camera = camera;
    }

    public void update() {
        Main.getBatch().setProjectionMatrix(camera.combined);
        Main.getBatch().draw(backgroundTexture, -(float) Gdx.graphics.getWidth() /2, -(float) Gdx.graphics.getHeight() /2);
    }

}
