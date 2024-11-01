package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class VisualTree implements VisualObject{
    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    private final String texturePath;
    private final Tree logicalTree;

    public VisualTree(String texturePath, Tree logicalTree) {
        texture = new Texture(texturePath);
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        this.logicalTree = logicalTree;
        this.texturePath = texturePath;
    }

    public VisualTree(VisualTree deepCopy, Tree logicalTree){
        texture = new Texture(deepCopy.texturePath);
        this.logicalTree = logicalTree;
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        this.texturePath = deepCopy.texturePath;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public float getRotation() {
        return logicalTree.getRotation();
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, graphics, rectangle, getRotation());
    }

    @Override
    public void dispose(){
        texture.dispose();
    }

    @Override
    public void processMotion(TileMovement tileMovement){
        // idle
    }

    @Override
    public GameObject getLogicalEntity() {
        return logicalTree;
    }
}
