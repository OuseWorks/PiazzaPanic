package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class OrderComponent implements Component {

    public Entity orderPlaced;
    public Entity orderReceived;
}
