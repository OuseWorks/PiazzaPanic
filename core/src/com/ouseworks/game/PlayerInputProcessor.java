package com.ouseworks.game;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.ouseworks.game.ecs.EventType;

public class PlayerInputProcessor extends InputAdapter {
    Signal gameEventSignal;
    public PlayerInputProcessor(Signal gameEventSignal){
        this.gameEventSignal=gameEventSignal;
    }

    @Override
    public boolean keyDown(int keyCode){
        if(keyCode== Input.Keys.SPACE){
            gameEventSignal.dispatch(EventType.CHECK_INTERACTIONS);
        }
        return true;
    }
}
