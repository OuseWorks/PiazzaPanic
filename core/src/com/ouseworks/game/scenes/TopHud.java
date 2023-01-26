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

public class TopHud {

    public Stage stage;
    private float timer;
    private float timeTaken;
    private int repPoints;
    private int customersRemaining;
    Label timerLabel;
    Label timeTakenLabel;
    Label repPointsLabel;
    Label repPointsRemainingLabel;
    Label customersLabel;
    Label customersRemainingLabel;

    TextButton helpButton;

    public TopHud(final Stage stage) {
        this.stage=stage;
        this.timeTaken= 0;

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3,3);
        timerLabel= new Label("Time taken   ", new Label.LabelStyle(font,Color.GOLD));
        repPointsLabel= new Label("Reputation points    ", new Label.LabelStyle(font,Color.GOLD));
        customersLabel= new Label("Customers remaining  ", new Label.LabelStyle(font,Color.GOLD));

        timeTakenLabel= new Label(Float.toString(timeTaken), new Label.LabelStyle(font,Color.WHITE));
        repPointsRemainingLabel= new Label("3", new Label.LabelStyle(font,Color.WHITE));
        customersRemainingLabel= new Label("5", new Label.LabelStyle(font,Color.WHITE));

        Skin skin = new Skin(Gdx.files.internal("OrderSkin/orderSkin.json"));

        helpButton = new TextButton("Help",skin);
        helpButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                HelpWindow helpWindow = new HelpWindow(stage);
                System.out.println("help was clicked");
            }
        });
        table.add(timerLabel).pad(10);
        table.add(repPointsLabel).pad(10);
        table.add(customersLabel).pad(10);
        table.add(helpButton).pad(10);
        table.row();
        table.add(timeTakenLabel).pad(10);
        table.add(repPointsRemainingLabel).pad(10);
        table.add(customersRemainingLabel).pad(10);
        stage.addActor(table);

    }

    public void setTime(int time){
        timeTakenLabel.setText(Integer.toString(time));
    }

    public void setCustomersRemaining(int customersRemaining){
        customersRemainingLabel.setText(Integer.toString(customersRemaining));
    }
    public void setReputationPoints(int reputationPoints){
        repPointsLabel.setText(Integer.toString(reputationPoints));
    }
    public void update(float dt){
        this.timer += dt;
        if(timer >= 1){
            timeTaken++;
            String text = Float.toString(timeTaken);
            timeTakenLabel.setText(text);
            this.timer = 0;
        }
    }
    public void dispose(){
        stage.dispose();
    }
}
