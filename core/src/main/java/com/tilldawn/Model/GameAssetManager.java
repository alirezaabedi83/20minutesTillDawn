package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    private final String character1_idle0 = "Images/Sprite/Idle/Idle_0.png";
    private final String character1_idle1 = "1/Idle_1.png";
    private final String character1_idle2 = "1/Idle_2.png";
    private final String character1_idle3 = "1/Idle_3.png";
    private final String character1_idle4 = "1/Idle_4.png";
    private final String character1_idle5 = "1/Idle_5.png";
    private final Texture character1_idle0_tex = new Texture(character1_idle0);
    private final Texture character1_idle1_tex = new Texture(character1_idle1);
    private final Texture character1_idle2_tex = new Texture(character1_idle2);
    private final Texture character1_idle3_tex = new Texture(character1_idle3);
    private final Texture character1_idle4_tex = new Texture(character1_idle4);
    private final Texture character1_idle5_tex = new Texture(character1_idle5);
    private final Texture treeMonster = new Texture("Images/Sprite/T/T_TreeMonster_0.png");
    private final Texture tentacleMonster = new Texture("Images/Sprite/T/T_TentacleEnemy_0.png");
    private final Texture eyebatMonster = new Texture("Images/Sprite/T/T_EyeBat_0.png");
    private final Texture elderMonster = new Texture("Images/Sprite/T/T_TreeMonster_0.png");

    // Provide getters
    public Texture getTreeMonster() {
        return treeMonster;
    }

    public Texture getTentacleMonster() {
        return tentacleMonster;
    }

    public Texture getEyebatMonster() {
        return eyebatMonster;
    }

    public Texture getElderMonster() {
        return elderMonster;
    }
    private final Animation<Texture> character1_idle_frames = new Animation<>(0.1f, character1_idle0_tex, character1_idle1_tex, character1_idle2_tex, character1_idle3_tex, character1_idle4_tex, character1_idle5_tex);


    private final Texture smgTexture =new Texture("smg/SMGStill.png");
    private final Texture shotgun = new Texture("Images/Texture2D/T/T_DualShotgun_Gun.png");
    private final Texture revolverTexture =new Texture("Images/Texture2D/T/T_Volley_Gun.png");
    private final Texture dualSmgTexture =new Texture("smg/T_DualSMGs_Icon.png");


    private final String bullet = "bullet.png";


    private GameAssetManager(){

    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Animation<Texture> getCharacter1_idle_animation() {
        return character1_idle_frames;
    }

    public String getCharacter1_idle0(){
        return character1_idle0;
    }

    public Texture getSmgTexture(){
        return smgTexture;
    }


    public String getBullet(){
        return bullet;
    }

    public String getCharacter1_idle1() {
        return character1_idle1;
    }

    public Texture getShotgun() {
        return shotgun;
    }

    public Texture getRevolverTexture() {
        return revolverTexture;
    }

    public Texture getDualSmgTexture() {
        return dualSmgTexture;
    }
}
