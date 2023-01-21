package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class RenderComponent implements Component, Pool.Poolable {

    public String visual;
    @Override
    public void reset() {

    }
}
