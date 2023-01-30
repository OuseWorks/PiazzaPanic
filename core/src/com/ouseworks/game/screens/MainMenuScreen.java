package com.ouseworks.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import com.ouseworks.game.PiazzaPanicGame;

public class MainMenuScreen implements Screen {

    final PiazzaPanicGame game;
    OrthographicCamera camera;

    public MainMenuScreen(final PiazzaPanicGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 960);
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        Texture titleImage = new Texture("titleImage.jpg");
        game.batch.begin();
        game.batch.draw(titleImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Welcome to Piazza Panic!!! ", 100, 500);
        game.font.draw(game.batch, "You are now the manager of the Piazza Restaurant kitchen. ", 100, 300);
        game.font.draw(game.batch, "Your objective is to serve the customers as fast as possible! Good luck! ", 100,
                200);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
