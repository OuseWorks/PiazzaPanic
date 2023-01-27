package com.ouseworks.game.systems.ordering;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ouseworks.game.components.ClickableComponent;
import com.ouseworks.game.components.PositionComponent;
import com.ouseworks.game.components.RenderComponent;
import com.ouseworks.game.ecs.EventType;

public class CustomerCounterSystem extends EntitySystem implements Listener {

    /*
    Chefs who click the counter entity will have their inventory checked to see if it matches
    the current order.

    The item will be removed from the chef's inventory if order matches and will fire the
    order completed signal.

    If not it will fire an incorrect order signal.
     */
    private Signal gameEventSignal;
    private EventType currentOrderRequest;
    private ComponentMapper<PositionComponent> inventoryComponent = ComponentMapper.getFor(PositionComponent.class);
    private ImmutableArray<Entity> chefs;
    public CustomerCounterSystem(Signal gameEventSignal){
        this.gameEventSignal=gameEventSignal;
        this.gameEventSignal.add(this);
    }

    @Override
    public void addedToEngine(Engine engine) {
        chefs = engine
                .getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class, ClickableComponent.class
                ).get());

        // Create counter entity which will hopefully get rendered by the render entity system?
    }

    @Override
    public void receive(Signal signal, Object object) {

        if(object.equals(EventType.REQUEST_BURGER)){
            // Only accept burgers.
            currentOrderRequest=EventType.REQUEST_BURGER;
            System.out.println("I am a customer who wants a burger");
        }

        if (object.equals(EventType.REQUEST_SALAD)) {
            // Only accept salad.
            currentOrderRequest=EventType.REQUEST_SALAD;
            System.out.println("I am a customer who wants a salad");
        }


        if(object.equals(EventType.COUNTER_CLICKED_BY_CHEF1)){
            /* If the counter is clicked then check
               the edible component of the item in the chef's inventory.

               if the edible component of the item is the correct food type
               then delete the item in the chef's inventory and fire
               the order completed event.
             */
        }
    }
}
