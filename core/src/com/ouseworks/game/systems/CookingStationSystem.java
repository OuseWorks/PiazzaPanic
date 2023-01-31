package com.ouseworks.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ouseworks.game.components.InventoryComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.ecs.EntityType;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.CookingStationWindow;

public class CookingStationSystem extends EntitySystem implements Listener {

    private Signal signal;
    private Stage stage;
    private Engine engine;
    private Entity currentChef;
    private ComponentMapper<InventoryComponent> inv = ComponentMapper.getFor(InventoryComponent.class);
    Sound sizzleSfx = Gdx.audio.newSound(Gdx.files.internal("sizzle.ogg"));
    Sound sighSfx = Gdx.audio.newSound(Gdx.files.internal("sigh.ogg"));

    public CookingStationSystem(Signal signal, Stage stage) {
        signal.add(this);
        this.signal = signal;
        this.stage = stage;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (engine);
    }

    @Override
    public void receive(Signal signal, Object object) {
        if (object.equals(EventType.USE_COOKING_STATION)) {
            CookingStationWindow c = new CookingStationWindow(stage, signal);
        }
        currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);

        if (object.equals(EventType.COOK_PATTY)) {
            if (inv.get(currentChef).items.contains(EntityType.PATTY)) {
                inv.get(currentChef).items.remove(EntityType.PATTY);
                inv.get(currentChef).items.add(EntityType.COOKED_PATTY);
                signal.dispatch(EventType.UPDATE_INVENTORY);
                sizzleSfx.play();
            } else {
                sighSfx.play();
            }
        }
    }

}
