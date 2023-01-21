package com.ouseworks.game.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;
import java.util.Stack;


public class InventoryComponent implements Component, Pool.Poolable {

    public Stack<Entity> items = new Stack<>();
    public int maxItems;
    @Override
    public void reset() {

    }


}
