package com.ouseworks.game.systems.ordering;

import java.text.BreakIterator;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.ouseworks.game.components.ClickableComponent;
import com.ouseworks.game.components.PositionComponent;
import com.ouseworks.game.components.RenderComponent;
import com.ouseworks.game.components.InventoryComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.ecs.EntityType;

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
    private ComponentMapper<InventoryComponent> inventoryComponent = ComponentMapper.getFor(InventoryComponent.class);
    private Entity currentChef;

    Sound thankYouSfx = Gdx.audio.newSound(Gdx.files.internal("thankyou.ogg"));
    Sound sighSfx = Gdx.audio.newSound(Gdx.files.internal("sigh.ogg"));

    public CustomerCounterSystem(Signal gameEventSignal){
        this.gameEventSignal=gameEventSignal;
        this.gameEventSignal.add(this);
    }

    @Override
    public void addedToEngine(Engine engine) {
        currentChef = engine
                .getEntitiesFor(Family.all(PositionComponent.class, RenderComponent.class, ClickableComponent.class,
                InventoryComponent.class, MoveableComponent.class).get()).first();
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
               the edible component of the item in the chef's inventory. */

            InventoryComponent inventory = inventoryComponent.get(currentChef);
            boolean correctDish = false;

            // Check that the chef has the requested dish
            for (EntityType item : inventory.items) {
                if (item == EntityType.BURGER && currentOrderRequest == EventType.REQUEST_BURGER) {
                    correctDish = true;
                    inventory.items.remove(item);
                    break;
                } else if (item == EntityType.SALAD && currentOrderRequest == EventType.REQUEST_SALAD) {
                    correctDish = true;
                    inventory.items.remove(item);
                    break;
                }
            }

            // Respond to the correct/incorrect dish
            if (correctDish) {
                gameEventSignal.dispatch(EventType.ORDER_COMPLETED);
                thankYouSfx.play();
            } else {
                gameEventSignal.dispatch(EventType.INCORRECT_ORDER);
                sighSfx.play();
            }
        }
    }
}
