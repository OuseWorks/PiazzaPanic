package com.ouseworks.game.scenes;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;


public class OrderHud{

    
    public Stage stage;
    public Label burgerRecipe;
    public Label saladRecipe;
    // private Viewport viewport;

    private Window orderWindow;

    public OrderHud(Stage stage) {
        this.stage = stage;
        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        // Add a window with a table inside it.
        // Then add the dynamic information inside the table.
        orderWindow = new Window("Orders", skin);
        orderWindow.setSize(280, 500);
        orderWindow.setPosition(0, stage.getHeight() / 2);

        BitmapFont font = new BitmapFont();
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        burgerRecipe = new Label("Burger x1: \n Cooked Patty x1 \n Lettuce x 1 \n Bun x1", new Label.LabelStyle(font,Color.GOLD));
        saladRecipe = new Label("Salad x1: \n Chopped Onion x1 \n Chopped Lettuce x1 \n Chopped Tomato x1", new Label.LabelStyle(font,Color.GOLD));
        // table.center().padRight(100);

        burgerRecipe.setVisible(false);
        saladRecipe.setVisible(false);

        orderWindow.add(burgerRecipe).padLeft(7);
        orderWindow.add(saladRecipe);
        

        orderWindow.addActor(table);
        stage.addActor(orderWindow);
    }



    public void update(float dt) {
        // Insert update logic.
    }

    // public void updateSalad(EntityType currentOrder){
        
    //     orderWindow.add(saladRecipe);
        
    // }


    public void dispose() {
        stage.dispose();
    }

    // @Override
    // public void receive(Signal signal, Object object){
    //     if(object.equals(EventType.REQUEST_BURGER)){
    //         orderWindow.getCell(burgerRecipe).getActor().setVisible(true);
    //     }
    //     if(object.equals(EventType.REQUEST_SALAD)){
    //         // saladRecipe.setVisible(true);
    //         orderWindow.add(saladRecipe);
    //     }
    //     if(object.equals(EventType.ORDER_COMPLETED)){
    //         orderWindow.clear();
    //     }

    // }

}
