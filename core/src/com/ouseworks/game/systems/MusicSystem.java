package com.ouseworks.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ouseworks.game.components.InventoryComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.ecs.EntityType;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.CookingStationWindow;

public class MusicSystem extends EntitySystem implements Listener {

    private Signal signal;
    private Engine engine;
    private Entity currentChef;
    
    private Music bgm = Gdx.audio.newMusic(Gdx.files.internal("bgm.ogg"));
    private boolean muted = false;

    public MusicSystem(Signal signal) {
        signal.add(this);
        this.signal = signal;
        
        bgm.setLooping(true);
        bgm.setVolume(1);
        bgm.play();
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = (engine);
    }

    @Override
    public void receive(Signal signal, Object object) {
        if (object.equals(EventType.TOGGLE_MUSIC)) {
            if (muted) {
                bgm.play();
            } else {
                bgm.pause();
            }
            
            muted = !muted;
        }
    }

    @Override
    public void removedFromEngine(Engine engine){
        bgm.dispose();
    }
}
