package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.ouseworks.game.ecs.EntityType;

public class InteractableComponent implements Component {
    public Rectangle zone;
    public EntityType type;
}
