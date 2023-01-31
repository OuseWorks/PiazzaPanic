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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CookingStationWindow {
    public Stage stage;
    public Window window;
    public ImageButton cookBurger;
    public Label cookBurgerLabel;

    public CookingStationWindow(Stage stage) {
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        window = new Window("Cooking Station", skin);
        window.setSize(600, 450);
        window.setPosition(Gdx.graphics.getWidth() / 2 - 300, Gdx.graphics.getHeight() / 2 - 225);

        Table table = new Table();
        table.setFillParent(true);

        Texture burgerTexture = new Texture(Gdx.files.internal("burger64.png"));
        Drawable burgerImage = new TextureRegionDrawable(new TextureRegion(burgerTexture));

        cookBurger = new ImageButton(burgerImage);
        cookBurgerLabel = new Label("Cook Burger", skin);
        table.add(cookBurgerLabel);
        table.row();
        table.add(cookBurger);

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
    }

}
