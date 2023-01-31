package com.ouseworks.game.screens;

import com.badlogic.ashley.signals.Listener;
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
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.Ingredients;
import com.ouseworks.game.scenes.InventoryHud;
import com.ouseworks.game.scenes.OrderHud;
import com.ouseworks.game.scenes.TopHud;
import com.ouseworks.game.systems.*;
import com.ouseworks.game.systems.ordering.CustomerCounterSystem;
import com.ouseworks.game.systems.ordering.CustomerOrderSystem;
import com.ouseworks.game.systems.preparation.FoodPreparationSystem;

public class GameScreen implements Screen, Listener {
    final PiazzaPanicGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TopHud topHud;
    private OrderHud orderHud;
    private Stage hudStage;
    private Ingredients ingredients;
    private InventoryHud inventory;
    private Viewport hudViewport;
    private TiledMapObjectHelper tiledMapObjectHelper;
    private EntityFactory entityFactory;

    private Signal gameEventSignal;
    boolean stopGame=false;
    public GameScreen(final PiazzaPanicGame game) {
        this.game = game;
        this.entityFactory = new EntityFactory(game.engine);
        this.gameEventSignal = new Signal();
        gameEventSignal.add(this);

        entityFactory.createCook(900, 300, "Chef1.png", true);
        entityFactory.createCook(900, 500, "Chef2.png", false);

        entityFactory.createCustomer(650, 600, "customer.png");
    }

    public void render(float delta) {

        orderHud.update(delta);
        topHud.update(delta);
        ingredients.update(delta);

        // Render Tilemap
        renderer.setView(camera);
        renderer.render();
        game.engine.update(delta);
        // Draw HUDS
        topHud.stage.draw();
        orderHud.stage.draw();
        ingredients.stage.draw();
        inventory.stage.draw();

        if(stopGame){
            String msg = "You finished the game in " + topHud.timeTaken + " seconds";
            game.setScreen(new GameOverScreen(game,msg));
            game.engine.removeAllSystems();
            game.engine.removeAllEntities();
            stopGame=false;
        }
    }

    @Override
    public void resize(int width, int height) {

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
        inventory = new InventoryHud(hudStage);
        ingredients = new Ingredients(hudStage, gameEventSignal);
        // Start systems, giving them access to the huds if needed.
        game.engine.addSystem(new RenderEntitySystem(camera, game.batch));
        game.engine.addSystem(new MoveEntitySystem((TiledMapTileLayer) map.getLayers().get("Walls"),gameEventSignal));
        game.engine.addSystem(new CollideEntitySystem());

        game.engine.addSystem(new ClickableSystem());
        game.engine.addSystem(new CustomerCounterSystem(gameEventSignal));
        game.engine.addSystem(new CustomerOrderSystem(gameEventSignal, topHud, orderHud));
        game.engine.addSystem(new DetectInteractionSystem(gameEventSignal));
        game.engine.addSystem(new InventorySystem(gameEventSignal,inventory));
        game.engine.addSystem(new FoodPreparationSystem(gameEventSignal, hudStage));
        game.engine.addSystem(new CookingStationSystem(gameEventSignal, hudStage));
        game.engine.addSystem(new MusicSystem(gameEventSignal));

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hudStage);
        inputMultiplexer.addProcessor(new PlayerInputProcessor(gameEventSignal,game.engine));
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
        hudStage.dispose();
    }

    @Override
    public void receive(Signal signal, Object object) {
        if(object.equals(EventType.GAME_FINISHED)){
            stopGame=true;
        }
    }
}
