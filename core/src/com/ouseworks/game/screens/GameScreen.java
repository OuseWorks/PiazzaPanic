package com.ouseworks.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

import com.ouseworks.game.EntityFactory;
import com.ouseworks.game.systems.RenderEntitySystem;
import com.ouseworks.game.PiazzaPanicGame;

public class GameScreen implements Screen {
    final PiazzaPanicGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    //private Chef currentChef;

    public GameScreen(final PiazzaPanicGame game) {
        this.game = game;
        EntityFactory entityFactory = new EntityFactory(game.engine);
        entityFactory.createCook1();
        entityFactory.createCook2();

    }

    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

        game.engine.update(delta);

        //this.game.batch.begin();
        //currentChef.sprite.draw(this.game.batch);
        //this.game.batch.end();
        /*
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            currentChef.sprite.setX(currentChef.sprite.getX() + (Gdx.graphics.getDeltaTime()) * currentChef.speed);
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            currentChef.sprite.setX(currentChef.sprite.getX() - (Gdx.graphics.getDeltaTime()) * currentChef.speed);
        if (Gdx.input.isKeyPressed(Keys.UP))
            currentChef.sprite.setY(currentChef.sprite.getY() + (Gdx.graphics.getDeltaTime()) * currentChef.speed);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            currentChef.sprite.setY(currentChef.sprite.getY() - (Gdx.graphics.getDeltaTime()) * currentChef.speed);

         */
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        TiledMapTileLayer layer0 = (TiledMapTileLayer) map.getLayers().get(0);
        Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2,
                layer0.getHeight() * layer0.getTileHeight() / 2, 0);
        camera.position.set(center);
        camera.update();

    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        System.out.println("I want to see the kitchen");
        camera = new OrthographicCamera();
        game.engine.addSystem(new RenderEntitySystem(camera,game.batch));

        // Chef chef1 = new Chef();
        //this.currentChef = chef1;

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

}