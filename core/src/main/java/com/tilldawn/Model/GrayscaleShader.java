package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class GrayscaleShader {
    private ShaderProgram shader;
    private float intensity = 1.0f;

    public GrayscaleShader() {
        String vertexShader = Gdx.files.internal("shaders/default.vert").readString();
        String fragmentShader = Gdx.files.internal("shaders/grayscale.frag").readString();

        shader = new ShaderProgram(vertexShader, fragmentShader);

        if (!shader.isCompiled()) {
            Gdx.app.error("Shader", shader.getLog());
        }
    }

    public ShaderProgram getShader() {
        return shader;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public float getIntensity() {
        return intensity;
    }

    public void begin() {
        shader.begin();
        shader.setUniformf("u_grayscaleIntensity", intensity);
    }

    public void end() {
        shader.end();
    }

    public void dispose() {
        shader.dispose();
    }
}