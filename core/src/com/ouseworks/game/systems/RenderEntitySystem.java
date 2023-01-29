package com.ouseworks.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ouseworks.game.components.*;

public class RenderEntitySystem extends EntitySystem {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ImmutableArray<Entity> cooks;
    private ImmutableArray<Entity> customers;

    public RenderEntitySystem(OrthographicCamera camera, SpriteBatch batch) {
        this.camera = camera;
        this.batch = batch;

    }

    @Override
    public void addedToEngine(Engine engine) {
        cooks = engine
                .getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class, ClickableComponent.class,
                        InventoryComponent.class, BusyComponent.class).get());

        customers = engine.getEntitiesFor(Family
                .all(PositionComponent.class, RenderComponent.class, BusyComponent.class).get());
    }

    public void update(float deltaTime) {
        PositionComponent position;
        camera.update();
        batch.begin();

        for (int i = 0; i < cooks.size(); ++i) {
            Entity e = cooks.get(i);
            position = pm.get(e);
            Texture texture = new Texture(e.getComponent(RenderComponent.class).visual);
            Sprite sprite = new Sprite(texture);
            sprite.setPosition(position.x, position.y);
            sprite.setSize(64, 64);
            sprite.draw(batch);
        }

        for (int i = 0; i < customers.size(); i++) {
            Entity e = customers.get(i);
            position = pm.get(e);
            Texture texture = new Texture(e.getComponent(RenderComponent.class).visual);
            Sprite sprite = new Sprite(texture);
            sprite.setPosition(position.x, position.y);
            sprite.setSize(64, 64);
            sprite.draw(batch);
        }

        batch.end();
    }
}
