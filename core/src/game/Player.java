package game;

import game.Position;
import game.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player {
    private Position position;
    private Sprite sprite;
    private int speed;
    
    public Player() {
        position = new Position(800, 400);
        sprite = new Sprite("chef.png");
        speed = 250;
    }

    public Texture getSprite() {
        return sprite.getSprite();
    }

    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }
    
    public void moveUp() {
        position.moveUp(speed * Gdx.graphics.getDeltaTime());
    }

    public void moveDown() {
        position.moveDown(speed * Gdx.graphics.getDeltaTime());
    }

    public void moveLeft() {
        position.moveLeft(speed * Gdx.graphics.getDeltaTime());
    }

    public void moveRight() {
        position.moveRight(speed * Gdx.graphics.getDeltaTime());
    }
}