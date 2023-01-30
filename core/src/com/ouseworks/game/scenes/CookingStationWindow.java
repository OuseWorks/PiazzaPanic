package com.ouseworks.game.scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class CookingStationWindow {
    public Stage stage;
    public Window window;

    public CookingStationWindow(Stage stage) {
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        window = new Window("Cooking Station", skin);
        window.setSize(600, 450);
        window.setPosition(Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 - 225);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(true);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.bottom().right();
        closeButton.setColor(Color.RED);
        closeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                window.remove();
            }
        });

        table.addActor(closeButton);
        window.addActor(table);
        stage.addActor(window);
    }

}
