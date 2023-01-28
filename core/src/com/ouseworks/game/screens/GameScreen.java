package com.ouseworks.game.screens;

import com.badlogic.ashley.signals.Signal;
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
import com.ouseworks.game.systems.CollideEntitySystem;
import com.ouseworks.game.systems.MoveEntitySystem;
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

    private Signal gameEventSignal;



    public GameScreen(final PiazzaPanicGame game) {
        this.game = game;
        this.gameEventSignal=new Signal();

        EntityFactory entityFactory = new EntityFactory(game.engine);

        entityFactory.createCook(300,300,"Chef1.png",true);
        entityFactory.createCook(200,500,"Chef2.png",false);

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
        game.engine.addSystem(new CollideEntitySystem());


        game.engine.addSystem(new ClickableSystem());
        game.engine.addSystem(new CustomerCounterSystem(gameEventSignal));
        game.engine.addSystem(new CustomerOrderSystem(gameEventSignal,topHud,orderHud));


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
