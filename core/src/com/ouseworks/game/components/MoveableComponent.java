package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MoveableComponent implements Component, Pool.Poolable {
    public float speed;

    @Override
    public void reset() {

    }
}
