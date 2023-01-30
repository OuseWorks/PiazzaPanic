package com.ouseworks.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ouseworks.game.components.InteractableComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.components.PositionComponent;
import com.ouseworks.game.ecs.EntityType;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.scenes.Ingredients;
import com.ouseworks.game.*;

public class DetectInteractionSystem extends EntitySystem implements Listener {
    Signal gameEventSignal;
    Engine engine;

    Entity currentChef;
    public Ingredients ingredients;

    ImmutableArray<Entity> stations;

    ComponentMapper<InteractableComponent> ic = ComponentMapper.getFor(InteractableComponent.class);
    ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);

    public DetectInteractionSystem(Signal gameEventSignal) {
        gameEventSignal.add(this);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
        currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);
        stations = engine.getEntitiesFor(Family.all(InteractableComponent.class).get());
    }

    @Override
    public void receive(Signal signal, Object object) {

        currentChef = engine.getEntitiesFor(Family.all(MoveableComponent.class).get()).get(0);

        if (object.equals(EventType.CHECK_INTERACTIONS)) {
            for (Entity station : stations) {
                if (ic.get(station).zone.contains(pc.get(currentChef).x, pc.get(currentChef).y * 20 / 17)
                        ||
                        ic.get(station).zone.contains(pc.get(currentChef).x, (pc.get(currentChef).y + 64) * 20 / 17)
                        ||
                        ic.get(station).zone.contains(pc.get(currentChef).x + 64, pc.get(currentChef).y * 20 / 17)
                        ||
                        ic.get(station).zone.contains(pc.get(currentChef).x + 64,
                                (pc.get(currentChef).y + 64) * 20 / 17)) {

                    System.out.println("chef interacting with" + ic.get(station).type);
                    if(ic.get(station).type == EntityType.INGREDIENT_STATION ){
                        Ingredients.ingredientWindow.setVisible(true);
                    }
                    // Decide what should happen next.
                }

            }

        }

    }
}
