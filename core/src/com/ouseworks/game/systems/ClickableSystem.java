package com.ouseworks.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ouseworks.game.components.*;

public class ClickableSystem extends EntitySystem {

    private ComponentMapper<ClickableComponent> cc = ComponentMapper.getFor(ClickableComponent.class);
    private ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
    private ImmutableArray<Entity> clickableEntities;

    public ClickableSystem() {

    }

    @Override
    public void addedToEngine(Engine engine) {
        clickableEntities = engine.getEntitiesFor(Family.all(ClickableComponent.class).get());

    }

    public void update(float delta) {

        for (int i = 0; i < clickableEntities.size(); i++) {
            ClickableComponent click = cc.get(clickableEntities.get(i));
            PositionComponent position = pc.get(clickableEntities.get(i));

            click.bounds.x = position.x;
            click.bounds.y = position.y;

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (click.bounds.contains(Gdx.input.getX(), Gdx.input.getY())) {
                }
            }
        }
    }
}
