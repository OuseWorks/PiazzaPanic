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
import com.ouseworks.game.scenes.Ingredients;

public class InventoryHud{

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



    public InventoryHud(final Stage stage){
        this.stage = stage;
        
        Table inventoryHud = new Table();
        inventoryHud.bottom();
        inventoryHud.setFillParent(true);
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2, 2);
        Lettuce = new Label("Lettuce:0 ", new Label.LabelStyle(font,Color.GOLD));
        Tomato = new Label("Tomato:0", new Label.LabelStyle(font,Color.GOLD));
        Patty = new Label("Patty:0", new Label.LabelStyle(font,Color.GOLD));

        inventoryHud.row().top();
        inventoryHud.add(lettuce).pad(10);
        inventoryHud.add(tomato).pad(10);
        inventoryHud.add(patty).pad(10);
        inventoryHud.row();
        inventoryHud.add(Lettuce).pad(10);
        inventoryHud.add(Tomato).pad(10);
        inventoryHud.add(Patty).pad(10);
        stage.addActor(inventoryHud);

    }

    


    public void update(float dt){
        Lettuce.setText("Lettuce:" + Ingredients.noLettuce); 
        Tomato.setText("Tomato:" + Ingredients.noTomato);
        Patty.setText("Patty:"+ Ingredients.noPatty);


    }

    public void dispose(){
        stage.dispose();
    }

}
