package com.ouseworks.game.components;

import com.badlogic.ashley.core.Component;

public class EdibleComponent implements Component {
    public boolean done;
    public boolean choppable;
    public boolean cookable;
    public int chopProgress;
    public int cookProgress;
}
