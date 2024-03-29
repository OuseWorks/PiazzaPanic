package com.ouseworks.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.ouseworks.game.components.ClickableComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.components.PositionComponent;
import com.ouseworks.game.components.RenderComponent;

public class MoveEntitySystem extends EntitySystem {
    private ComponentMapper<PositionComponent> posComp = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<MoveableComponent> moveComp = ComponentMapper.getFor(MoveableComponent.class);
    private ImmutableArray<Entity> players;
    private int currentChef = 0;
    private TiledMapTileLayer collisionLayer;
    private Engine engine;
    private Signal gameEventSignal;

    public MoveEntitySystem(TiledMapTileLayer collisionLayer, Signal gameEventSignal) {
        this.collisionLayer = collisionLayer;
        this.gameEventSignal=gameEventSignal;

    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
        players = engine
                .getEntitiesFor(
                        Family.all(PositionComponent.class, RenderComponent.class, ClickableComponent.class).get());
    }

    public void update(float deltaTime) {
        PositionComponent position;
        MoveableComponent moveable;

        // chef switching using players list
        if (Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)) {
            players.get(currentChef).remove(MoveableComponent.class);
            if (currentChef < players.size() - 1) {
                currentChef++;

            } else {
                currentChef = 0;
            }
            players.get(currentChef).add(engine.createComponent(MoveableComponent.class));

        }

        // player movement of currently selected chef
        position = posComp.get(players.get(currentChef));
        moveable = moveComp.get(players.get(currentChef));

        int oldX = position.x;
        int oldY = position.y;
        int newX = oldX;
        int newY = oldY;
        int playerHeight = 64;
        int playerWidth = 64;

        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
            newX += moveable.speed * Gdx.graphics.getDeltaTime();
            if (!isCellBlocked(newX, oldY) && (!isCellBlocked(newX + playerWidth, oldY))
                    && (!isCellBlocked(newX + playerWidth, oldY + playerHeight)) && (!isCellBlocked(
                            newX, oldY + playerHeight))) {
                position.x = newX;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
            newX -= moveable.speed * Gdx.graphics.getDeltaTime();
            if (!isCellBlocked(newX, oldY) && (!isCellBlocked(newX + playerWidth, oldY))
                    && (!isCellBlocked(newX + playerWidth, oldY + playerHeight)) && (!isCellBlocked(
                            newX, oldY + playerHeight))) {
                position.x = newX;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
            newY += moveable.speed * Gdx.graphics.getDeltaTime();
            if (!isCellBlocked(oldX, newY) && (!isCellBlocked(oldX + playerWidth, newY))
                    && (!isCellBlocked(oldX + playerWidth, newY + playerHeight)) && (!isCellBlocked(
                            oldX, newY + playerHeight))) {
                position.y = newY;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
            newY -= moveable.speed * Gdx.graphics.getDeltaTime();
            if (!isCellBlocked(oldX, newY) && (!isCellBlocked(oldX + playerWidth, newY))
                    && (!isCellBlocked(oldX + playerWidth, newY + playerHeight)) && (!isCellBlocked(
                            oldX, newY + playerHeight))) {
                position.y = newY;
            }
        }
    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()),
                (int) (((y * 20 / 17) / collisionLayer.getTileHeight())));

        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

}