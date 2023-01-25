package com.ouseworks.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HelpWindow {

    public Stage stage;
    //private Viewport viewport;

    private Window helpWindow;



    public HelpWindow(Stage stage) {
        this.stage=stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        // Add a window with a table inside it.
        //Then add the dynamic information inside the table.
        Stack table = new Stack();
        helpWindow = new Window("Help",skin);
        helpWindow.setSize(200,500);
        helpWindow.setPosition(stage.getWidth()/2,stage.getHeight()/2);
        TextButton closeButton = new TextButton("Close",skin);
        closeButton.bottom().center();

        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                helpWindow.remove();
            }
        });

        table.addActor(closeButton);
        helpWindow.add(table);

        stage.addActor(helpWindow);
    }

    public void update(float dt){
        // Insert update logic.
    }

    public void dispose(){
        stage.dispose();
    }
}
