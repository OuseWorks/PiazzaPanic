package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Sprite {
    private String imagePath;
    private Texture texture;
    
    public Sprite(String filepath) {
        imagePath = filepath;
        texture = new Texture(Gdx.files.internal(imagePath));
    }
    
    public Texture getSprite() {
        return texture;
    }
}
