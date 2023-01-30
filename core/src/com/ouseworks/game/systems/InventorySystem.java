package com.ouseworks.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ouseworks.*;
import com.ouseworks.game.components.InventoryComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.ecs.EntityType;

public class InventorySystem  extends EntitySystem implements Listener{

    /*
     * Adds an ingredient to the currently selected chef when
     * chef interacts with the ingredient station.
     * 
     * Listen for a signal.
     */

    private Entity currentChef;
    private ComponentMapper<InventoryComponent> inv = ComponentMapper.getFor(InventoryComponent.class);
    public Engine engine;
    public Signal signal;

    public InventorySystem(Signal signal){
        signal.add(this);
    }

    @Override
    public void addedToEngine(Engine engine){
        currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);
        this.engine = engine;

    }

    @Override
    public void receive(Signal signal, Object object) {
        // When ingredient station menu button clicked
        // the object will be the thing that needs to be added
        // to inventory. 
        if(object instanceof EntityType){
            currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);
            inv.get(currentChef).items.add((EntityType)object);
            System.out.println("Inventory update");
        }





        
    }


    
}
