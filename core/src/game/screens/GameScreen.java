package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

import game.Chef;
import game.piazzapanic.PiazzaPanicGame;

public class GameScreen implements Screen {
    final PiazzaPanicGame game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Chef currentChef;

    public GameScreen(final PiazzaPanicGame game) {
        this.game = game;
    }
    
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

        this.game.batch.begin();
        this.game.batch.draw(currentChef.getSprite(), currentChef.getX(), currentChef.getY());
        this.game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) { currentChef.moveUp(); }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) { currentChef.moveDown(); }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) { currentChef.moveLeft(); }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { currentChef.moveRight(); }
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
        Chef chef1 = new Chef();
        this.currentChef = chef1;

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