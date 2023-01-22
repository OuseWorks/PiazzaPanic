package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class ClickableComponent implements Component {
    public Rectangle bounds = new Rectangle(0,0,100,100);
}
