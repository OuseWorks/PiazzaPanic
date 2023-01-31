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
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.InventoryHud;

import java.util.Collections;

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
    private InventoryHud invHud;

    public InventorySystem(Signal signal, InventoryHud invHud){
        signal.add(this);
        this.invHud=invHud;

    }

    @Override
    public void addedToEngine(Engine engine){
        currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);
        this.engine = engine;
        currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);


    }

    public void updateInventoryHud(Entity currentChef){
        // sets the inventory hud to match the current chef's inventory.
        //update current chef
        //currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);

        int lettuceCount = Collections.frequency(inv.get(currentChef).items,EntityType.LETTUCE);
        int tomatoCount = Collections.frequency(inv.get(currentChef).items,EntityType.TOMATO);
        int onionCount = Collections.frequency(inv.get(currentChef).items,EntityType.ONION);
        int bunCount = Collections.frequency(inv.get(currentChef).items,EntityType.BUN);
        int pattyCount = Collections.frequency(inv.get(currentChef).items,EntityType.PATTY);
        int cookedPattyCount = Collections.frequency(inv.get(currentChef).items,EntityType.COOKED_PATTY);
        int choppedLettuceCount = Collections.frequency(inv.get(currentChef).items,EntityType.CHOPPED_LETTUCE);
        int choppedTomatoCount = Collections.frequency(inv.get(currentChef).items,EntityType.CHOPPED_TOMATO);
        int choppedOnionCount = Collections.frequency(inv.get(currentChef).items,EntityType.CHOPPED_ONION);
        int burgerCount = Collections.frequency(inv.get(currentChef).items,EntityType.BURGER);
        int saladCount = Collections.frequency(inv.get(currentChef).items,EntityType.SALAD);

        invHud.updateInventory(lettuceCount,tomatoCount,onionCount,pattyCount,bunCount,cookedPattyCount,
                burgerCount,saladCount,choppedLettuceCount,choppedOnionCount,choppedTomatoCount);


    }

    @Override
    public void receive(Signal signal, Object object) {

        if(object instanceof EntityType){
            currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);
            inv.get(currentChef).items.add((EntityType)object);
            System.out.println("Inventory update");
            // update the inventory hud...
            updateInventoryHud(currentChef);
        }

        if(object.equals(EventType.UPDATE_INVENTORY)){
            currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);
            updateInventoryHud(currentChef);
        }





        
    }


    
}
