package com.ouseworks.game.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.CookingStationWindow;

public class CookingStationSystem extends EntitySystem implements Listener {

    private Signal signal;
    private Stage stage;

    public CookingStationSystem(Signal signal, Stage stage) {
        signal.add(this);
        this.signal = signal;
        this.stage = stage;
    }

    @Override
    public void receive(Signal signal, Object object) {
        if (object.equals(EventType.USE_COOKING_STATION)) {
            System.out.println("Opening cooking station");
            CookingStationWindow c = new CookingStationWindow(stage);
        }
    }

}
