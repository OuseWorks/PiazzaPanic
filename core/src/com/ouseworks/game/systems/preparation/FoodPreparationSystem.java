package com.ouseworks.game.systems.preparation;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.PreparationWindow;

public class FoodPreparationSystem extends EntitySystem implements Listener {

    private Signal gameEventSignal;
    private Stage stage;
    public FoodPreparationSystem(Signal gameEventSignal, Stage stage){
        gameEventSignal.add(this);
        this.gameEventSignal=gameEventSignal;
        this.stage=stage;
    }

    @Override
    public void addedToEngine(Engine engine){

    }
    @Override
    public void receive(Signal signal, Object object) {
        if(object.equals(EventType.USE_PREPARATION_STATION)){
            System.out.println("Opening preparation station");
            PreparationWindow prepWindow = new PreparationWindow(this.stage);
        }
    }
}
