package game;

import com.badlogic.gdx.math.Rectangle;

public class Position {
    private float x;
    private float y;

    private Rectangle rect;
    
    public Position(float x, float y) {
        rect.width = 64;
        rect.height = 64;

        this.x = x; rect.x = x;
        this.y = y; rect.y = y;
    }
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void moveUp(float amount) {
        x += amount;
        rect.y += amount;
    }

    public void moveDown(float amount) {
        rect.y -= amount;
    }

    public void moveLeft(float amount) {
        rect.x -= amount;
    }

    public void moveRight(float amount) {
        rect.x += amount;
    }
}
