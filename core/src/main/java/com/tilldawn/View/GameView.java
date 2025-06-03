package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;

public class GameView implements Screen, InputProcessor {
    private GameController controller;
    private Stage stage;
    private OrthographicCamera camera;
    private Skin skin;

    public GameView(GameController controller, Skin skin) {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.controller = controller;
        controller.setView(this,camera);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        ScreenUtils.clear(0, 0, 0, 1);


        // Update game state


        // Begin sprite batch with camera projection

        Main.getBatch().begin();
        Main.getBatch().setProjectionMatrix(camera.combined);

        controller.updateGame(delta);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // WorldController handles background drawing
        // EnemySpawner handles enemy drawing
        // PlayerController handles player drawing
        // WeaponController handles weapon and bullet drawing

        Main.getBatch().end();

        // Draw UI (HUD)
//        drawHUD();

        // Update stage


    }

    private void drawHUD() {
        Main.getBatch().begin();
        skin.getFont("default").draw(Main.getBatch(),
                "Health: " + (int)controller.getPlayerController().getPlayer().getPlayerHealth(),
                20, Gdx.graphics.getHeight() - 20);
        skin.getFont("default").draw(Main.getBatch(),
                "Ammo: " + controller.getWeaponController().getWeapon().getAmmo() + "/" +
                        controller.getWeaponController().getWeapon().getMaxAmmo(),
                20, Gdx.graphics.getHeight() - 50);
        skin.getFont("default").draw(Main.getBatch(),
                "Time: " + (int)(Game.getInstance().getTotalGameTime() - Game.getInstance().getElapsedTime()) + "s",
                Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 20);
        skin.getFont("default").draw(Main.getBatch(),
                "Level: " + controller.getPlayerController().getLevel(),
                Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 50);
        Main.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
