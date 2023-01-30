package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.ouseworks.game.ecs.EntityType;

public class EdibleComponent implements Component {

    public EntityType foodType;
    public boolean done;
    public boolean choppable;
    public boolean cookable;
    public int chopProgress;
    public int cookProgress;
}
