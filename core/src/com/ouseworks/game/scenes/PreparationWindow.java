package com.ouseworks.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PreparationWindow {
    public Stage stage;
    public Window prepWindow;

    public PreparationWindow(Stage stage){
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        // Add a window with a table inside it.
        // Then add the dynamic information inside the table.
        Table table = new Table();
        prepWindow = new Window("Preparation Station - Assemble Dishes or Chop Things", skin);
        prepWindow.setSize(500, 400);
        prepWindow.setPosition(Gdx.graphics.getWidth()/2 -300,Gdx.graphics.getHeight()/2 -225);
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.bottom().center();

        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                prepWindow.remove();
            }
        });

        table.addActor(closeButton);
        prepWindow.add(table);

        stage.addActor(prepWindow);
    }
}
