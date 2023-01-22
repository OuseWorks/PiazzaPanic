package com.ouseworks.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ouseworks.game.EntityFactory;
import com.ouseworks.game.TopHud;
import com.ouseworks.game.systems.RenderEntitySystem;
import com.ouseworks.game.systems.MoveEntitySystem;
import com.ouseworks.game.PiazzaPanicGame;

public class GameScreen implements Screen {
    final PiazzaPanicGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TopHud hud;
    private Stage stage;



    public GameScreen(final PiazzaPanicGame game) {
        this.game = game;
        EntityFactory entityFactory = new EntityFactory(game.engine);
        entityFactory.createCook(300,400,200,"Chef1.png");
        entityFactory.createCook(200,500,200,"Chef2.png");
        entityFactory.createCustomer(600,600,game.engine.getEntities().get(0),"Item.png");
    }

    public void render(float delta) {
        hud.update(delta);
        renderer.setView(camera);
        renderer.render();
        game.engine.update(delta);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); //set the spriteBatch to draw what our stageViewport sees
        hud.stage.draw();
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
        camera = new OrthographicCamera();

        game.engine.addSystem(new RenderEntitySystem(camera,game.batch));
        game.engine.addSystem(new MoveEntitySystem());
        hud = new TopHud(game.batch,500);
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
