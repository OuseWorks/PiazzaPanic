package com.ouseworks.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TopHud {

    public Stage stage;
    private Viewport viewport;
    private float timer;
    private float gameTimer;
    private int repPoints;
    private int customersRemaining;
    Label timerLabel;
    Label timeRemainingLabel;
    Label repPointsLabel;
    Label repPointsRemainingLabel;
    Label customersLabel;
    Label customersRemainingLabel;

    public TopHud(SpriteBatch spriteBatch, float timer) {
        this.gameTimer= (float) timer;
        viewport=new ScreenViewport();
        stage = new Stage(viewport,spriteBatch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3,3);
        timerLabel= new Label("Time remaining   ", new Label.LabelStyle(font,Color.GOLD));
        repPointsLabel= new Label("Reputation points    ", new Label.LabelStyle(font,Color.GOLD));
        customersLabel= new Label("Customers remaining  ", new Label.LabelStyle(font,Color.GOLD));

        timeRemainingLabel= new Label(Float.toString(gameTimer), new Label.LabelStyle(font,Color.WHITE));
        repPointsRemainingLabel= new Label("10", new Label.LabelStyle(font,Color.WHITE));
        customersRemainingLabel= new Label("5", new Label.LabelStyle(font,Color.WHITE));

        table.add(timerLabel).pad(10);
        table.add(repPointsLabel).pad(10);
        table.add(customersLabel).pad(10);
        table.row();
        table.add(timeRemainingLabel).pad(10);
        table.add(repPointsRemainingLabel).pad(10);
        table.add(customersRemainingLabel).pad(10);
        stage.addActor(table);

    }

    public void update(float dt){

        this.timer += dt;
        if(timer >= 1){
            if (gameTimer > 0) {
                gameTimer--;
            }
            String text = Float.toString(gameTimer);
            timeRemainingLabel.setText(text);
            this.timer = 0;
        }
    }

    public void dispose(){
        stage.dispose();
    }
}
