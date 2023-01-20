package game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Chef {
    public Texture texture;
    public Sprite sprite;
    public int speed;

    public Chef() {
        this.texture = new Texture("chef.png");
        this.sprite = new Sprite(texture);
        this.speed = 200;
        sprite.setPosition(10, 10);
    }

}
