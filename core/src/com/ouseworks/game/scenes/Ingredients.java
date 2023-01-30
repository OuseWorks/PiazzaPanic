package com.ouseworks.game.scenes;

import com.ouseworks.game.*;
import com.ouseworks.game.components.MoveableComponent;
import com.ouseworks.game.ecs.EntityType;
import com.ouseworks.game.systems.InventorySystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;

public class Ingredients{

    public Stage stage;

    //public InventoryComponent Inventory;
    public static Window ingredientWindow;
    public static int noLettuce;
    public static int noTomato;
    public static int noPatty;
    public static int noOnion;
    public static int noBuns;
    private ComponentMapper<MoveableComponent> move = ComponentMapper.getFor(MoveableComponent.class);
    private Entity currentChef;
    public Engine engine;
    

    public Ingredients(Stage stage, Signal signal){
        this.stage = stage;
        //this.signal = signal;

        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        Table ingredients = new Table();
        ingredientWindow = new Window("Select Ingredient",skin);
        ingredientWindow.setSize(600, 450);
        ingredientWindow.setPosition(Gdx.graphics.getWidth()/2 -300,Gdx.graphics.getHeight()/2 -225);
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.bottom().center();

        Texture lettuceTexture = new Texture(Gdx.files.internal("lettuce64.png"));
        Texture tomatoTexture = new Texture(Gdx.files.internal("tomatoes64.png"));
        Texture pattyTexture = new Texture(Gdx.files.internal("patty64.png"));
        Texture onionTexture = new Texture(Gdx.files.internal("onion64.png"));
        Texture bunTexture = new Texture(Gdx.files.internal("bun64.png"));
        Drawable lettuceIMG = new TextureRegionDrawable(new TextureRegion(lettuceTexture));
        Drawable tomatoIMG = new TextureRegionDrawable(new TextureRegion(tomatoTexture));
        Drawable pattyIMG = new TextureRegionDrawable(pattyTexture);
        Drawable onionIMG = new TextureRegionDrawable(onionTexture);
        Drawable bunIMG = new TextureRegionDrawable(bunTexture);




        ImageButton lettuce = new ImageButton(lettuceIMG);
        lettuce.top().center();
        ImageButton tomatoes = new ImageButton(tomatoIMG);
        tomatoes.top().left();
        ImageButton patty = new ImageButton(pattyIMG);
        patty.top().right();
        ImageButton onion = new ImageButton(onionIMG);
        ImageButton bun = new ImageButton(bunIMG);

    

        // functionality for the close button to close the panel
        closeButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ingredientWindow.setVisible(false);;

            }
        });

        final Signal gameSignal = signal;
        // functionality for the ingredient buttons 
        lettuce.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent click, Actor actor){
                noLettuce = noLettuce + 1;
                gameSignal.dispatch(EntityType.LETTUCE);
                ingredientWindow.setVisible(false);;

            }
            
        });
        
        tomatoes.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent click, Actor actor){
                noTomato++;
                gameSignal.dispatch(EntityType.TOMATO);
                ingredientWindow.setVisible(false);;
            }

        });

        patty.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent click, Actor actor){
                noPatty++;
                gameSignal.dispatch(EntityType.PATTY);
                ingredientWindow.setVisible(false);

            }

        });

        onion.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent click, Actor actor){
                noOnion++;
                gameSignal.dispatch(EntityType.ONION);
                ingredientWindow.setVisible(false);
            }
        });

        bun.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent click, Actor actor){
                noBuns++;
                gameSignal.dispatch(EntityType.BUN);
                ingredientWindow.setVisible(false);
            }
        });



        ingredients.top();
        ingredients.setFillParent(true);

        ingredients.add(lettuce);
        ingredients.add(tomatoes);
        ingredients.add(patty);
        ingredients.add(onion);
        ingredients.add(bun);
        ingredients.row();
        ingredients.add(closeButton).center().bottom();

        ingredientWindow.addActor(ingredients);
        ingredientWindow.add(ingredients);
        ingredientWindow.add(lettuce);
        ingredientWindow.add(tomatoes);
        ingredientWindow.add(patty);
        ingredientWindow.add(onion);
        ingredientWindow.add(bun);
        ingredientWindow.add(closeButton);
        ingredientWindow.setVisible(false);
        stage.addActor(ingredientWindow);

    }

    public void update(float dt){        
        

        

    }

    public void dispose(){
        stage.dispose();
    }



}