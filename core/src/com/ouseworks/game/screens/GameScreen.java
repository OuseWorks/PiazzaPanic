package com.ouseworks.game.screens;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ouseworks.game.*;
import com.ouseworks.game.scenes.OrderHud;
import com.ouseworks.game.scenes.TopHud;
import com.ouseworks.game.systems.*;
import com.ouseworks.game.systems.ordering.CustomerCounterSystem;
import com.ouseworks.game.systems.ordering.CustomerOrderSystem;

public class GameScreen implements Screen {
    final PiazzaPanicGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TopHud topHud;
    private OrderHud orderHud;
    private Stage hudStage;
    private Viewport hudViewport;
    private TiledMapObjectHelper tiledMapObjectHelper;
    private EntityFactory entityFactory;

    private Signal gameEventSignal;

    public GameScreen(final PiazzaPanicGame game) {
        this.game = game;
        this.entityFactory = new EntityFactory(game.engine);
        this.gameEventSignal = new Signal();

        entityFactory.createCook(900, 300, "Chef1.png", true);
        entityFactory.createCook(900, 500, "Chef2.png", false);

        entityFactory.createCustomer(600, 600, game.engine.getEntities().get(0), "customer.png");
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
        /*
         * camera.viewportWidth = width;
         * camera.viewportHeight = height;
         * TiledMapTileLayer layer0 = (TiledMapTileLayer) map.getLayers().get(0);
         * Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2,
         * layer0.getHeight() * layer0.getTileHeight() / 2, 0);
         * camera.position.set(center);
         * camera.update();
         */
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("KitchenMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / 64f);
        this.tiledMapObjectHelper = new TiledMapObjectHelper(this, entityFactory);
        camera = new OrthographicCamera();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 30, 20);
        camera.update();

        hudViewport = new ScreenViewport();
        hudStage = new Stage(hudViewport, game.batch);


        // Create Huds
        topHud = new TopHud(hudStage);
        orderHud = new OrderHud(hudStage);
        // Start systems, giving them access to the huds if needed.
        game.engine.addSystem(new RenderEntitySystem(camera, game.batch));
        game.engine.addSystem(new MoveEntitySystem((TiledMapTileLayer) map.getLayers().get("Walls")));
        game.engine.addSystem(new CollideEntitySystem());

        game.engine.addSystem(new ClickableSystem());
        game.engine.addSystem(new CustomerCounterSystem(gameEventSignal));
        game.engine.addSystem(new CustomerOrderSystem(gameEventSignal, topHud, orderHud));
        game.engine.addSystem(new DetectInteractionSystem(gameEventSignal));

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hudStage);
        inputMultiplexer.addProcessor(new PlayerInputProcessor(gameEventSignal));
        Gdx.input.setInputProcessor(inputMultiplexer);

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
