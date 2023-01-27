package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.ouseworks.game.ecs.FoodType;

public class EdibleComponent implements Component {

    public FoodType foodType;
    public boolean done;
    public boolean choppable;
    public boolean cookable;
    public int chopProgress;
    public int cookProgress;
}
