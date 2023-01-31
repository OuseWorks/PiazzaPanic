package com.ouseworks.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class HelpWindow {

    public Stage stage;
    // private Viewport viewport;

    private Window helpWindow;
    private Label helpText;

    public HelpWindow(Stage stage) {
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        // Add a window with a table inside it.
        // Then add the dynamic information inside the table.
        Table table = new Table();
        helpWindow = new Window("Help", skin);
        helpWindow.setSize(500, 500);
        helpWindow.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.bottom();

        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                helpWindow.remove();
            }
        });

        BitmapFont font = new BitmapFont();

        helpText = new Label("press space to open station and serve food, m to mute.", new Label.LabelStyle(font, Color.BLUE));
        table.add(helpText).center();
        helpText.setPosition(table.getWidth()/2,table.getHeight()/2);
        helpWindow.add(table);
        table.row();
        table.add(closeButton);
        stage.addActor(helpWindow);
    }

    public void update(float dt) {
        // Insert update logic.
    }

    public void dispose() {
        stage.dispose();
    }
}
