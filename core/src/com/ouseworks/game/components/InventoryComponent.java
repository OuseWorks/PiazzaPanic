package com.ouseworks.game.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;
import com.ouseworks.game.ecs.EntityType;

import java.util.Stack;


public class InventoryComponent implements Component{

    public Stack<EntityType> items = new Stack<>();

}
