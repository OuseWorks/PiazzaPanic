package com.ouseworks.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.ouseworks.game.components.ClickableComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.components.PositionComponent;
import com.ouseworks.game.components.RenderComponent;

public class MoveEntitySystem extends EntitySystem {
    private ComponentMapper<PositionComponent> posComp = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<MoveableComponent> moveComp = ComponentMapper.getFor(MoveableComponent.class);
    private ImmutableArray<Entity> players;
    private int currentChef = 0;

    private Engine engine;

    @Override
    public void addedToEngine(Engine engine) {
        this.engine=engine;
        players = engine
                .getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class, ClickableComponent.class
                        ).get());
    }

    public void update(float deltaTime) {
        PositionComponent position;
        MoveableComponent moveable;

        if (Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)) {
            players.get(currentChef).remove(MoveableComponent.class);
            if (currentChef < players.size() - 1) {
                currentChef++;

            } else {
                currentChef = 0;
            }
            players.get(currentChef).add(engine.createComponent(MoveableComponent.class));

            System.out.println(currentChef);
        }

        position = posComp.get(players.get(currentChef));
        moveable = moveComp.get(players.get(currentChef));

        // TODO @mattrohatynskyj implement a system for cycling through chefs as a
        // current chef
        // this way we only need to check the chef selected (i.e. no for loop) :)
        // furthermore this means we only check 1 entity against all objects instead of
        // all chefs against all collidable objects

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            position.x += moveable.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            position.x -= moveable.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            position.y += moveable.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            position.y -= moveable.speed * Gdx.graphics.getDeltaTime();
        }
    }
}