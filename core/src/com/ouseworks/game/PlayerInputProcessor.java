package com.ouseworks.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.components.RenderComponent;
import com.ouseworks.game.ecs.EventType;

public class PlayerInputProcessor extends InputAdapter {
    Signal gameEventSignal;
    Engine engine;
    public PlayerInputProcessor(Signal gameEventSignal, Engine engine){
        this.gameEventSignal=gameEventSignal;
        this.engine=engine;
    }

    @Override
    public boolean keyDown(int keyCode){
        if(keyCode== Input.Keys.SPACE){
            gameEventSignal.dispatch(EventType.CHECK_INTERACTIONS);
        }

        if(keyCode == Input.Keys.CONTROL_LEFT){
            //System.out.println("the chef"+engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0).getComponent(RenderComponent.class).visual);
            gameEventSignal.dispatch(EventType.UPDATE_INVENTORY);

        }
        return true;
    }
}
