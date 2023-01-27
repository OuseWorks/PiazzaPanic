package com.ouseworks.game.scenes;

import com.ouseworks.game.scenes.Ingredients;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Inventory{

    public Stage stage;

    public Label Lettuce;
    public Label Tomato;
    public Label Patty;

    Texture lettuceTexture = new Texture(Gdx.files.internal("lettuce64.png"));
    Texture tomatoTexture = new Texture(Gdx.files.internal("tomatoes64.png"));
    Texture pattyTexture = new Texture(Gdx.files.internal("patty64.png"));

    Image lettuce = new Image(lettuceTexture);
    Image tomato = new Image(tomatoTexture);
    Image patty = new Image(pattyTexture);



    public Inventory(final Stage stage){
        this.stage = stage;

        Table inventory = new Table();
        inventory.bottom();
        inventory.setFillParent(true);
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2, 2);
        Lettuce = new Label("Lettuce:0 ", new Label.LabelStyle(font,Color.GOLD));
        Tomato = new Label("Tomato:0", new Label.LabelStyle(font,Color.GOLD));
        Patty = new Label("Patty:0", new Label.LabelStyle(font,Color.GOLD));

        Lettuce.setText("Lettuce:" + Ingredients.noLettuce()); 

        inventory.row().top();
        inventory.add(lettuce).pad(10);
        inventory.add(tomato).pad(10);
        inventory.add(patty).pad(10);
        inventory.row();
        inventory.add(Lettuce).pad(10);
        inventory.add(Tomato).pad(10);
        inventory.add(Patty).pad(10);
        stage.addActor(inventory);

    }


    public void update(float dt){

    }

    public void dispose(){
        stage.dispose();
    }

}
