package com.ouseworks.game.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;
import com.ouseworks.game.ecs.EntityType;

import java.util.Stack;


public class InventoryComponent implements Component{

    public Stack<EntityType> items = new Stack<>();

    public InventoryComponent() {
        for (int i = 0; i < 10; i++) {
            items.push(EntityType.BURGER);
            items.push(EntityType.SALAD);
        }
    }
}
