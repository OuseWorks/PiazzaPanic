package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class BusyComponent implements Component {
    public boolean occupied;
    public Entity occupiedWith;
}
