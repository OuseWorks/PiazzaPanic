package com.ouseworks.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.ouseworks.game.components.CollideableComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.components.PositionComponent;

public class CollideEntitySystem extends EntitySystem {
    private ComponentMapper<PositionComponent> posComp = ComponentMapper.getFor(PositionComponent.class);
    private ImmutableArray<Entity> allEntities;
    private ImmutableArray<Entity> moveableEntities;
    
    // TODO: use the real entity size
    // Assumed size of all entities
    float width = 64;
    float height = 64;

    @Override
    public void addedToEngine(Engine engine) {
        allEntities = engine
                .getEntitiesFor(Family.all(CollideableComponent.class, 
                        PositionComponent.class).get());
        moveableEntities = engine
                .getEntitiesFor(Family.all(CollideableComponent.class, 
                        MoveableComponent.class,
                        PositionComponent.class).get());
    }

    public void update(float deltaTime) {
        // Check for collisions between all entities that are able to collide
        for (Entity a : moveableEntities) {
            for (Entity b : allEntities) {
                if (a == b) { continue; }
                if (areColliding(a, b)) {
                    System.out.print("collision!");
                }
            }
        }
    }
    
    private boolean areColliding(Entity a, Entity b) {
        PositionComponent positionA = posComp.get(a);
        PositionComponent positionB = posComp.get(b);

        float leftA = positionA.x;
        float rightA = positionA.x + width;
        float topA = positionA.y;
        float bottomA = positionA.y + height;

        float leftB = positionB.x;
        float rightB = positionB.x + width;
        float topB = positionB.y;
        float bottomB = positionB.y + height;
        
        boolean a_right_b = leftA > rightB;
        boolean a_left_b = rightA < leftB;
        boolean a_above_b = bottomA < topB;
        boolean a_below_b = topA > bottomB;

        return !(a_right_b
            || a_left_b
            || a_above_b
            || a_below_b);
    }
}