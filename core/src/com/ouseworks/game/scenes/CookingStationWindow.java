package com.ouseworks.game.scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ouseworks.game.ecs.EventType;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CookingStationWindow {
    public Stage stage;
    public Window window;
    public ImageButton cookPatty;
    public Label cookPattyLabel;

    public CookingStationWindow(Stage stage, Signal gameEventSignal) {
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        window = new Window("Cooking Station", skin);
        window.setSize(600, 450);
        window.setPosition(Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 - 225);

        Table table = new Table();
        table.setFillParent(true);

        Texture pattyTexture = new Texture(Gdx.files.internal("patty64.png"));
        Drawable pattyImage = new TextureRegionDrawable(new TextureRegion(pattyTexture));

        cookPatty = new ImageButton(pattyImage);
        cookPattyLabel = new Label("Cook Patty", skin);
        table.add(cookPattyLabel);
        table.row();
        table.add(cookPatty);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.setColor(Color.RED);
        closeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                window.remove();
            }
        });

        table.addActor(closeButton);
        closeButton.top().right();

        window.addActor(table);
        stage.addActor(window);

        final Signal signal = gameEventSignal;
        cookPatty.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                signal.dispatch(EventType.COOK_PATTY);
                window.remove();
            }
        });

    }

}
