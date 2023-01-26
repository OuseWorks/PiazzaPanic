package com.ouseworks.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import com.ouseworks.game.PiazzaPanicGame;

public class GameOverScreen implements Screen {

    final PiazzaPanicGame game;
    OrthographicCamera camera;

    private String gameOverMessage;

    public GameOverScreen(final PiazzaPanicGame game, String msg) {
        this.game = game;
        this.gameOverMessage=msg;


    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1600, 960);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Game Over!", 100, 300);
        game.font.draw(game.batch,this.gameOverMessage,100,200);
        game.font.draw(game.batch,"Click to play again",100,100);
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
