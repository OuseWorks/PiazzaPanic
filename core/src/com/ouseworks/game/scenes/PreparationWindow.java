package com.ouseworks.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PreparationWindow {
    public Stage stage;
    public Window prepWindow;
    public ImageButton chopLettuce;
    public ImageButton chopTomato;
    public ImageButton chopOnion;
    public ImageButton assembleBurger;
    public ImageButton assembleSalad;
    public Label chopLettuceLabel;
    public Label chopTomatoLabel;
    public Label chopOnionLabel;
    public Label assembleBurgerLabel;
    public Label assembleSaladLabel;

    public PreparationWindow(Stage stage){
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        // Add a window with a table inside it.
        // Then add the dynamic information inside the table.
        Table table = new Table();
        prepWindow = new Window("Preparation Station - Assemble Dishes or Chop Things", skin);
        prepWindow.setSize(900, 400);
        prepWindow.setPosition(Gdx.graphics.getWidth()/2 -300,Gdx.graphics.getHeight()/2 -225);
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.bottom().center();

        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                prepWindow.remove();
            }
        });

        //table.addActor(closeButton);
        Texture lettuceTexture = new Texture(Gdx.files.internal("lettuce64.png"));
        Drawable lettuceIMG = new TextureRegionDrawable(new TextureRegion(lettuceTexture));
        Texture tomatoTexture = new Texture(Gdx.files.internal("tomatoes64.png"));
        Drawable tomatoIMG = new TextureRegionDrawable(new TextureRegion(tomatoTexture));
        Texture onionTexture = new Texture(Gdx.files.internal("onion64.png"));
        Drawable onionIMG = new TextureRegionDrawable(new TextureRegion(onionTexture));
        Texture burgerTexture = new Texture(Gdx.files.internal("burger64.png"));
        Drawable burgerIMG = new TextureRegionDrawable(new TextureRegion(burgerTexture));
        Texture saladTexture = new Texture(Gdx.files.internal("salad64.png"));
        Drawable saladIMG = new TextureRegionDrawable(new TextureRegion(saladTexture));

        chopLettuce = new ImageButton(lettuceIMG);
        chopTomato= new ImageButton(tomatoIMG);
        chopOnion = new ImageButton(onionIMG);
        assembleBurger= new ImageButton(burgerIMG);
        assembleSalad = new ImageButton(saladIMG);
        chopLettuceLabel = new Label("Chop Lettuce",skin);
        chopTomatoLabel = new Label("Chop Tomatos",skin);
        chopOnionLabel = new Label("Chop Onions",skin);
        assembleBurgerLabel= new Label("Assemble Burger",skin);
        assembleSaladLabel = new Label("Assemble Salad",skin);
        table.add(chopLettuceLabel).pad(20);
        table.add(chopTomatoLabel).pad(20);
        table.add(chopOnionLabel).pad(20);
        table.add(assembleBurgerLabel).pad(20);
        table.add(assembleSaladLabel).pad(20);
        table.row();
        table.add(chopLettuce);
        table.add(chopTomato);
        table.add(chopOnion);
        table.add(assembleBurger);
        table.add(assembleSalad);
        table.row();
        table.add(closeButton);
        prepWindow.add(table);

        stage.addActor(prepWindow);

    }
}
