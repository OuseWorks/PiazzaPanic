package com.ouseworks.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ouseworks.game.*;
import com.ouseworks.game.scenes.OrderHud;
import com.ouseworks.game.scenes.TopHud;
import com.ouseworks.game.systems.ClickableSystem;
import com.ouseworks.game.systems.RenderEntitySystem;
import com.ouseworks.game.systems.MoveEntitySystem;

public class GameScreen implements Screen {
    final PiazzaPanicGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TopHud topHud;
    private OrderHud orderHud;
    private Stage hudStage;
    private Viewport hudViewport;



    public GameScreen(final PiazzaPanicGame game) {
        this.game = game;
        EntityFactory entityFactory = new EntityFactory(game.engine);

        entityFactory.createCook(300,400,200,"Chef1.png");
        entityFactory.createCook(200,500,200,"Chef2.png");
        entityFactory.createCustomer(600,600,game.engine.getEntities().get(0),"Item.png");
    }

    public void render(float delta) {

        orderHud.update(delta);
        topHud.update(delta);

        // Render Tilemap
        renderer.setView(camera);
        renderer.render();
        game.engine.update(delta);
        // Draw HUDS
        topHud.stage.draw();
        orderHud.stage.draw();
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
        map = new TmxMapLoader().load("KitchenMapV2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        hudViewport=new ScreenViewport();
        hudStage = new Stage(hudViewport,game.batch);
        Gdx.input.setInputProcessor(hudStage);

        // Create Huds
        topHud = new TopHud(hudStage);
        orderHud = new OrderHud(hudStage);
        // Start systems, giving them access to the huds if needed.
        game.engine.addSystem(new RenderEntitySystem(camera, game.batch));
        game.engine.addSystem(new MoveEntitySystem());
        game.engine.addSystem(new ClickableSystem());
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
