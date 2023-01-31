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
    public Label Onions;
    public Label Buns;
    public Label Chopped_Lettuce;
    public Label Chopped_Onions;
    public Label Chopped_Tomatoes;
    public Label Cooked_Patty;
    public Label Burger;
    public Label Salad;


    Texture lettuceTexture = new Texture(Gdx.files.internal("lettuce64.png"));
    Texture tomatoTexture = new Texture(Gdx.files.internal("tomatoes64.png"));
    Texture pattyTexture = new Texture(Gdx.files.internal("patty64.png"));
    Texture onionTexture = new Texture(Gdx.files.internal("onion64.png"));
    Texture bunTexture = new Texture(Gdx.files.internal("bun64.png"));

    Texture burgerTexture = new Texture(Gdx.files.internal("burger64.png"));
    Texture saladTexture = new Texture(Gdx.files.internal("salad64.png"));


    Image lettuce = new Image(lettuceTexture);
    Image lettuce2 = new Image(lettuceTexture);
    Image tomato = new Image(tomatoTexture);
    Image tomato2 = new Image(tomatoTexture);
    Image patty = new Image(pattyTexture);
    Image patty2 = new Image(pattyTexture);
    Image onion = new Image(onionTexture);
    Image onion2 = new Image(onionTexture);
    Image bun = new Image(bunTexture);

    Image burger = new Image(burgerTexture);
    Image salad = new Image(saladTexture);



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
        Onions = new Label("Onions:0", new Label.LabelStyle(font,Color.GOLD));
        Buns = new Label("Buns:0", new Label.LabelStyle(font,Color.GOLD));
        Burger = new Label("Burgers:0", new Label.LabelStyle(font,Color.GOLD));
        Salad = new Label("Salads:0", new Label.LabelStyle(font,Color.GOLD));
        Cooked_Patty = new Label("Cooked Patty:0", new Label.LabelStyle(font,Color.GOLD));
        Chopped_Lettuce = new Label("Chopped Lettuce:0", new Label.LabelStyle(font,Color.GOLD));
        Chopped_Onions = new Label("Chopped Onions:0", new Label.LabelStyle(font,Color.GOLD));
        Chopped_Tomatoes = new Label("Chopped Tomatoes:0", new Label.LabelStyle(font,Color.GOLD));



        inventoryHud.row().top();
        inventoryHud.add(lettuce).pad(5);
        inventoryHud.add(tomato).pad(5);
        inventoryHud.add(patty).pad(5);
        inventoryHud.add(onion).pad(5);
        inventoryHud.add(bun).pad(5);
        inventoryHud.add(lettuce2).pad(5);
        inventoryHud.add(tomato2).pad(5);
        inventoryHud.add(onion2).pad(5);
        inventoryHud.add(patty2).pad(5);
        inventoryHud.add(burger).pad(5);
        inventoryHud.add(salad).pad(5);
        inventoryHud.row();
        inventoryHud.add(Lettuce).pad(5);
        inventoryHud.add(Tomato).pad(5);
        inventoryHud.add(Patty).pad(5);
        inventoryHud.add(Onions).pad(5);
        inventoryHud.add(Buns).pad(5);
        inventoryHud.add(Chopped_Lettuce).pad(5);
        inventoryHud.add(Chopped_Tomatoes).pad(5);
        inventoryHud.add(Chopped_Onions).pad(5);
        inventoryHud.add(Cooked_Patty).pad(5);
        inventoryHud.add(Burger).pad(5);
        inventoryHud.add(Salad).pad(5);
        stage.addActor(inventoryHud);

    }

    public void updateInventory(int lettuceCount, int tomatoCount, int onionCount, int pattyCount,
                                int bunCount, int cookedPattyCount, int burgerCount, int saladCount,
                                int choppedLettuceCount, int choppedOnionCount, int choppedTomatoCount){
        // Set the inventory to these values
        // Can be given from th
        Lettuce.setText("Lettuce:" + lettuceCount);
        Tomato.setText("Tomato:" + tomatoCount);
        Patty.setText("Patty:"+ pattyCount);
        Onions.setText("Onions:" + onionCount);
        Buns.setText("Buns:" + bunCount);
        Chopped_Tomatoes.setText("Chopped Tomatoes:" + choppedTomatoCount);
        Chopped_Onions.setText("Chopped Onions:" + choppedOnionCount);
        Chopped_Lettuce.setText("Chopped Lettuce:" + choppedLettuceCount);
        Cooked_Patty.setText("Cooked Patty:" + cookedPattyCount);
        Burger.setText("Burgers:"+burgerCount);
        Salad.setText("Salad:"+saladCount);
    }

    

/*
    public void update(float dt){
        Lettuce.setText("Lettuce:" + Ingredients.noLettuce); 
        Tomato.setText("Tomato:" + Ingredients.noTomato);
        Patty.setText("Patty:"+ Ingredients.noPatty);
        Onions.setText("Onions:" + Ingredients.noOnion);
        Buns.setText("Buns:" + Ingredients.noBuns);

    }
*/
    public void dispose(){
        stage.dispose();
    }

}
