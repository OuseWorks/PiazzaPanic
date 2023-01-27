package com.ouseworks.game.scenes;

import com.ouseworks.game.scenes.Inventory;
import com.badlogic.ashley.core.Entity;
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

    private Window ingredientWindow;
    public int noLettuce;
    public int noTomato;
    public int noPatty;

    public Ingredients(Stage stage){
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        Table ingredients = new Table();
        ingredientWindow = new Window("Select Ingredient",skin);
        ingredientWindow.setSize(600, 450);
        ingredientWindow.setPosition(4,stage.getHeight()/2);
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.bottom().center();

        Texture lettuceTexture = new Texture(Gdx.files.internal("lettuce64.png"));
        Texture tomatoTexture = new Texture(Gdx.files.internal("tomatoes64.png"));
        Texture pattyTexture = new Texture(Gdx.files.internal("patty64.png"));
        Drawable lettuceIMG = new TextureRegionDrawable(new TextureRegion(lettuceTexture));
        Drawable tomatoIMG = new TextureRegionDrawable(new TextureRegion(tomatoTexture));
        Drawable pattyIMG = new TextureRegionDrawable(pattyTexture);



        ImageButton lettuce = new ImageButton(lettuceIMG);
        lettuce.top().center();
        ImageButton tomatoes = new ImageButton(tomatoIMG);
        tomatoes.top().left();
        ImageButton patty = new ImageButton(pattyIMG);
        patty.top().right();

    

        // functionality for the close button to close the panel
        closeButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ingredientWindow.remove();
            }
        });

        // functionality for the ingredient buttons 
        lettuce.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent click, Actor actor){
                noLettuce++;
                
                ingredientWindow.remove();

            //     Lettuce.setText("Lettuce x", + noLettuce);
            }
            
        });
        
        
        
        


        tomatoes.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent click, Actor actor){
                noTomato++;
            }

        });

        patty.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent click, Actor actor){
                noPatty++;
            }

        });



        ingredients.top();
        ingredients.setFillParent(true);

        ingredients.add(closeButton);
        ingredients.add(lettuce);
        ingredients.add(tomatoes);
        ingredients.add(patty);

        ingredientWindow.addActor(ingredients);
        ingredientWindow.add(closeButton);
        ingredientWindow.add(ingredients);
        ingredientWindow.add(lettuce);
        ingredientWindow.add(tomatoes);
        ingredientWindow.add(patty);

        stage.addActor(ingredientWindow);
        
    }

    public void update(float dt){

    }

    public void dispose(){
        stage.dispose();
    }

    public int noLettuce(){
        return noLettuce;
    }

}