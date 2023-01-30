package com.ouseworks.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class OrderHud {

    public Stage stage;
    // private Viewport viewport;

    private Window orderWindow;

    public OrderHud(Stage stage) {
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        // Add a window with a table inside it.
        // Then add the dynamic information inside the table.
        orderWindow = new Window("Orders", skin);
        orderWindow.setSize(200, 500);
        orderWindow.setPosition(0, stage.getHeight() / 2);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        orderWindow.addActor(table);
        stage.addActor(orderWindow);
    }

    public void update(float dt) {
        // Insert update logic.
    }

    public void dispose() {
        stage.dispose();
    }
}
