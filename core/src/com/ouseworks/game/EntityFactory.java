package com.ouseworks.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.ashley.core.Engine;
import com.ouseworks.game.components.*;

public class EntityFactory {

    public Engine engine;

    public EntityFactory(Engine engine) {
        this.engine = engine;
    }

    // TODO Explore possibility of creating config files to store the description of
    // entities.
    public void createCook(int x, int y, String img, boolean movable) {
        final Entity cook = engine.createEntity();
        cook.add(engine.createComponent(PositionComponent.class));
        cook.add(engine.createComponent(RenderComponent.class));
        if (movable) {
            cook.add(engine.createComponent(MoveableComponent.class));
            // Set cook movement speed
            // cook.getComponent(MoveableComponent.class).speed = ;
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

    public void createCustomer(int x, int y, Entity orderPlaced, String img) {
        final Entity customer = engine.createEntity();
        customer.add(engine.createComponent(PositionComponent.class));
        customer.add(engine.createComponent(RenderComponent.class));

        customer.add(engine.createComponent(BusyComponent.class));
        customer.add(engine.createComponent(CollideableComponent.class));
        // Set customer spawn location.
        customer.getComponent(PositionComponent.class).x = 100;
        customer.getComponent(PositionComponent.class).y = 100;
        // Set customer sprite image
        customer.getComponent(RenderComponent.class).visual = img;

        engine.addEntity(customer);
    }

    public void createStation(Rectangle r, String stationType) {
        // TODO: @ben260 or @mzyzc whoever is more used to creating entities in ashley
        // ecs implement this
        System.out.println("created station: " + stationType);
    }
}
