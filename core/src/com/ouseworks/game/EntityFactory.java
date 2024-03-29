package com.ouseworks.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.ashley.core.Engine;
import com.ouseworks.game.components.*;
import com.ouseworks.game.ecs.EntityType;

public class EntityFactory {

    public Engine engine;

    public EntityFactory(Engine engine) {
        this.engine = engine;
    }


    public void createCook(int x, int y, String img, boolean movable) {
        final Entity cook = engine.createEntity();
        cook.add(engine.createComponent(PositionComponent.class));
        cook.add(engine.createComponent(RenderComponent.class));
        if (movable) {
            cook.add(engine.createComponent(MoveableComponent.class));
        }
        cook.add(engine.createComponent(ClickableComponent.class));
        cook.add(engine.createComponent(InventoryComponent.class));
        cook.add(engine.createComponent(BusyComponent.class));
        cook.add(engine.createComponent(CollideableComponent.class));

        // Set cook spawn location.
        cook.getComponent(PositionComponent.class).x = x;
        cook.getComponent(PositionComponent.class).y = y;

        // Set cook sprite image
        cook.getComponent(RenderComponent.class).visual = img;

        engine.addEntity(cook);
    }

    public void createCustomer(int x, int y, String img) {
        final Entity customer = engine.createEntity();
        customer.add(engine.createComponent(PositionComponent.class));
        customer.add(engine.createComponent(RenderComponent.class));

        customer.add(engine.createComponent(BusyComponent.class));
        customer.add(engine.createComponent(CollideableComponent.class));
        // Set customer spawn location.
        customer.getComponent(PositionComponent.class).x = x;
        customer.getComponent(PositionComponent.class).y = y;
        // Set customer sprite image
        customer.getComponent(RenderComponent.class).visual = img;

        engine.addEntity(customer);
    }


    public void createStation(Rectangle r, EntityType stationType) {
        Entity e = engine.createEntity();
        e.add(engine.createComponent(InventoryComponent.class));
        e.add(engine.createComponent(InteractableComponent.class));
        e.getComponent(InteractableComponent.class).type=stationType;
        e.getComponent(InteractableComponent.class).zone=r;
        engine.addEntity(e);
    }
    

}
