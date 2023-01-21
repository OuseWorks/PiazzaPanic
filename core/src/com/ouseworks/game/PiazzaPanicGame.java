package com.ouseworks.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.ouseworks.game.ecs.ECSEngine;
import com.ouseworks.game.screens.MainMenuScreen;

public class PiazzaPanicGame extends Game {
	public ECSEngine engine;
	public SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		this.engine = new ECSEngine();

		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
