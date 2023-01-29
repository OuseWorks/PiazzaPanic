package com.ouseworks.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.ouseworks.game.ecs.EntityType;
import com.ouseworks.game.screens.GameScreen;

public class TiledMapObjectHelper {

    private TiledMap tiledMap;
    private EntityFactory entityFactory;

    public TiledMapObjectHelper(GameScreen gameScreen, EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
        tiledMap = new TmxMapLoader().load("KitchenMap.tmx");
        parseMapObjects(tiledMap.getLayers().get("Obstacles").getObjects());
    }

    private void parseMapObjects(MapObjects mapObjects) {
        for (MapObject mapObject : mapObjects) {
            if (mapObject instanceof RectangleMapObject) {
                CreateObject((RectangleMapObject) mapObject);
            }
        }
    }

    private void CreateObject(RectangleMapObject rectangleMapObject) {
        Rectangle r = rectangleMapObject.getRectangle();
        entityFactory.createStation(r, EntityType.COUNTER);
    }
}
