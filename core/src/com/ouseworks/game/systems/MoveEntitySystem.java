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

    @Override
    public void addedToEngine (Engine engine) {
        players = engine.getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class, ClickableComponent.class,
                MoveableComponent.class).get());
    }

    public void update (float deltaTime) {
        PositionComponent position;
        MoveableComponent moveable;

        for (int i = 0; i < players.size(); ++i) {
            Entity player = players.get(i);
            position = posComp.get(player);
            moveable = moveComp.get(player);

            //System.out.println(moveable.speed * Gdx.graphics.getDeltaTime());

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
}
