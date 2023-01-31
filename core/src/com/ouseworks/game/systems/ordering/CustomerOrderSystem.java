package com.ouseworks.game.systems.ordering;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.ouseworks.game.ecs.EventType;
import com.ouseworks.game.ecs.EntityType;
import com.ouseworks.game.scenes.OrderHud;
import com.ouseworks.game.scenes.TopHud;

import java.util.Random;

public class CustomerOrderSystem extends EntitySystem implements Listener {

    private Signal gameEventSignal;
    private Engine engine;
    private int customersRemaining;
    private EntityType currentOrder;
    private Random rand;
    private TopHud topHud;
    private OrderHud orderHud;


    public CustomerOrderSystem(Signal gameEventSignal, TopHud topHud, OrderHud orderHud){
        // TODO Also take in the top hud and order hud so can update variables from these variables!

        rand = new Random();
        // Listen for game events
        this.gameEventSignal=gameEventSignal;
        this.gameEventSignal.add(this);

        // Access to HUDS
        this.topHud=topHud;
        this.orderHud=orderHud;

    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine=engine;
        this.customersRemaining=5;
        createNewOrder();
        updateDisplays();
    }

    public void createNewOrder(){
        int decision = rand.nextInt(2);
        if(decision==0){
            currentOrder= EntityType.BURGER;
            gameEventSignal.dispatch(EventType.REQUEST_BURGER);
            orderHud.burgerRecipe.setVisible(true);
            // Fire Event Request Burger
        }
        else if(decision==1){
            currentOrder= EntityType.SALAD;
            gameEventSignal.dispatch(EventType.REQUEST_SALAD);
            orderHud.saladRecipe.setVisible(true);
            
            // Fire Event Request Salad
        }
    }

    public void updateDisplays(){
        this.topHud.setCustomersRemaining(customersRemaining);
        
    }

    @Override
    public void receive(Signal signal, Object object) {
        if(object.equals(EventType.ORDER_COMPLETED)){
            orderHud.saladRecipe.setVisible(false);
            orderHud.burgerRecipe.setVisible(false);
            System.out.println("Order completed!");
            customersRemaining--;
            if(customersRemaining==0){
                // Fire game finished event!
                gameEventSignal.dispatch(EventType.GAME_FINISHED);
            }
            else{
                // TODO Notify user that order has been completed.
                // Choose new order
                createNewOrder();
                // Update hud
                updateDisplays();
            }
        }
    }
}
