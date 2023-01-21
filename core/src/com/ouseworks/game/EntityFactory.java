package com.ouseworks.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;
import com.ouseworks.game.components.PositionComponent;
import com.ouseworks.game.components.RenderComponent;

public class EntityFactory {

    public Engine engine;
    public EntityFactory(Engine engine){
        this.engine=engine;
    }

    public void createCook1(){
        final Entity cook1 = engine.createEntity();
        cook1.add(engine.createComponent(PositionComponent.class));
        cook1.add(engine.createComponent(RenderComponent.class));
        // Set cook1 spawn location.
        cook1.getComponent(PositionComponent.class).x=300;
        cook1.getComponent(PositionComponent.class).y=300;
        // Set cook1 sprite image
        cook1.getComponent(RenderComponent.class).visual="Chef1.png";
        engine.addEntity(cook1);

    }

    public void createCook2(){
        final Entity cook2 = engine.createEntity();
        cook2.add(engine.createComponent(PositionComponent.class));
        cook2.add(engine.createComponent(RenderComponent.class));
        // Set cook2 spawn location.
        cook2.getComponent(PositionComponent.class).x=100;
        cook2.getComponent(PositionComponent.class).y=100;
        // Set cook2 sprite image
        cook2.getComponent(RenderComponent.class).visual="Chef2.png";
        engine.addEntity(cook2);
    }
}
