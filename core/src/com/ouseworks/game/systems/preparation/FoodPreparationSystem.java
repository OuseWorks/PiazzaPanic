package com.ouseworks.game.systems.preparation;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ouseworks.game.components.InventoryComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.ecs.EntityType;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.PreparationWindow;

public class FoodPreparationSystem extends EntitySystem implements Listener {

    private Signal gameEventSignal;
    private Stage stage;
    private Engine engine;
    private Entity currentChef;
    private ComponentMapper<InventoryComponent> inv = ComponentMapper.getFor(InventoryComponent.class);
    Sound sighSfx = Gdx.audio.newSound(Gdx.files.internal("sigh.ogg"));


    public FoodPreparationSystem(Signal gameEventSignal, Stage stage){
        gameEventSignal.add(this);
        this.gameEventSignal=gameEventSignal;
        this.stage=stage;
    }

    @Override
    public void addedToEngine(Engine engine){
        this.engine=engine;
    }
    @Override
    public void receive(Signal signal, Object object) {
        if(object.equals(EventType.USE_PREPARATION_STATION)){
            System.out.println("Opening preparation station");
            PreparationWindow prepWindow = new PreparationWindow(this.stage,gameEventSignal);
        }
        currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);

        if(object.equals(EventType.CHOP_LETTUCE)){

            if(inv.get(currentChef).items.contains(EntityType.LETTUCE)){
                inv.get(currentChef).items.remove(EntityType.LETTUCE);
                inv.get(currentChef).items.add(EntityType.CHOPPED_LETTUCE);
                gameEventSignal.dispatch(EventType.UPDATE_INVENTORY);
            }
            else{
                sighSfx.play();
            }
        }

        if(object.equals(EventType.CHOP_TOMATO)){
            if(inv.get(currentChef).items.contains(EntityType.TOMATO)){
                inv.get(currentChef).items.remove(EntityType.TOMATO);
                inv.get(currentChef).items.add(EntityType.CHOPPED_TOMATO);
                gameEventSignal.dispatch(EventType.UPDATE_INVENTORY);
            }
            else{
                sighSfx.play();
            }
        }

        if(object.equals(EventType.CHOP_ONION)){
            if(inv.get(currentChef).items.contains(EntityType.ONION)){
                inv.get(currentChef).items.remove(EntityType.ONION);
                inv.get(currentChef).items.add(EntityType.CHOPPED_ONION);
                gameEventSignal.dispatch(EventType.UPDATE_INVENTORY);
            }
            else{
                sighSfx.play();
            }
        }

        if(object.equals(EventType.ASSEMBLE_BURGER)){
            if(inv.get(currentChef).items.contains(EntityType.LETTUCE)
            && inv.get(currentChef).items.contains(EntityType.BUN)
            && inv.get(currentChef).items.contains(EntityType.COOKED_PATTY)){
                inv.get(currentChef).items.remove(EntityType.BUN);
                inv.get(currentChef).items.remove(EntityType.COOKED_PATTY);
                inv.get(currentChef).items.remove(EntityType.LETTUCE);
                inv.get(currentChef).items.add(EntityType.BURGER);
                gameEventSignal.dispatch(EventType.UPDATE_INVENTORY);
            }
            else{
                sighSfx.play();
            }
        }

        if(object.equals(EventType.ASSEMBLE_SALAD)){
            if(inv.get(currentChef).items.contains(EntityType.CHOPPED_LETTUCE)
                    && inv.get(currentChef).items.contains(EntityType.CHOPPED_ONION)
                    && inv.get(currentChef).items.contains(EntityType.CHOPPED_TOMATO)){
                inv.get(currentChef).items.remove(EntityType.CHOPPED_ONION);
                inv.get(currentChef).items.remove(EntityType.CHOPPED_LETTUCE);
                inv.get(currentChef).items.remove(EntityType.CHOPPED_TOMATO);
                inv.get(currentChef).items.add(EntityType.SALAD);
                gameEventSignal.dispatch(EventType.UPDATE_INVENTORY);
            }
            else{
                sighSfx.play();
            }
        }

    }
}
