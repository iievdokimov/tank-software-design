package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class VisualObject{
    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    private final String texturePath;

    public VisualObject(String texturePath) {
        texture = new Texture(texturePath);
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);

        this.texturePath = texturePath;
    }

    VisualObject(VisualObject deepCopy){
        texture = new Texture(deepCopy.texturePath);
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        this.texturePath = deepCopy.texturePath;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextureRegion getGraphics() {
        return graphics;
    }

    public void dispose(){
        texture.dispose();
    }
}