package com.ouseworks.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ouseworks.game.components.CollideableComponent;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.components.PositionComponent;

public class CollideEntitySystem extends EntitySystem {
    private ComponentMapper<PositionComponent> posComp = ComponentMapper.getFor(PositionComponent.class);
    private ImmutableArray<Entity> allEntities;
    private ImmutableArray<Entity> moveableEntities;

    // TODO: use the real entity size
    // Assumed size of all entities
    int width = 100;
    int height = 100;

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
                if (a == b) {
                    continue;
                }
                if (!areColliding(a, b)) {
                    continue;
                }

                int[] collisionPos = getCollisionPos(a, b);

                PositionComponent positionA = posComp.get(a);
                positionA.x = collisionPos[0];
                positionA.y = collisionPos[1];
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

    private int[] getCollisionPos(Entity a, Entity b) {
        PositionComponent positionA = posComp.get(a);
        PositionComponent positionB = posComp.get(b);

        int[] collisionPos = { positionA.x, positionA.y };

        int leftA = positionA.x;
        int rightA = positionA.x + width;
        int topA = positionA.y;
        int bottomA = positionA.y + height;

        int leftB = positionB.x;
        int rightB = positionB.x + width;
        int topB = positionB.y;
        int bottomB = positionB.y + height;

        int depressionLeft = rightA - leftB;
        int depressionRight = rightB - leftA;
        int depressionTop = bottomB - topA;
        int depressionBottom = bottomA - topB;

        int smallest = Integer.min(depressionLeft, depressionRight);
        smallest = Integer.min(smallest, depressionTop);
        smallest = Integer.min(smallest, depressionBottom);

        if (smallest == depressionLeft) {
            // Left-side collision
            collisionPos = new int[] { leftB - width, positionA.y };
        } else if (smallest == depressionRight) {
            // Right-side collision
            collisionPos = new int[] { rightB, positionA.y };
        } else if (smallest == depressionTop) {
            // Top-side collision
            collisionPos = new int[] { positionA.x, topB + height };
        } else if (smallest == depressionBottom) {
            // Bottom-side collision
            collisionPos = new int[] { positionA.x, bottomB - 2 * height };
        }

        return collisionPos;
    }
}