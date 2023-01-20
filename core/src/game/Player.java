package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Texture sprite;
    private Rectangle rect;
    private int speed;
    
    public Player() {
        sprite = new Texture(Gdx.files.internal("chef.png"));
        rect = new Rectangle();
        rect.x = 20;
        rect.x = 20;
        rect.width = 64;
        rect.height = 64;
        
        speed = 100;
    }

    public Texture getSprite() {
        return sprite;
    }

    public float getX() {
        return rect.x;
    }

    public float getY() {
        return rect.y;
    }
    
    public void moveUp() {
        rect.y += speed * Gdx.graphics.getDeltaTime();
    }

    public void moveDown() {
        rect.y -= speed * Gdx.graphics.getDeltaTime();
    }

    public void moveLeft() {
        rect.x -= speed * Gdx.graphics.getDeltaTime();
    }

    public void moveRight() {
        rect.x += speed * Gdx.graphics.getDeltaTime();
    }
}