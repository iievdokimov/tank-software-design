package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class VisualTank implements VisualObject{
    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    private final String texturePath;
    private final Tank logicalTank;

    public VisualTank(String texturePath, Tank logicalTank) {
        texture = new Texture(texturePath);
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        this.logicalTank = logicalTank;
        this.texturePath = texturePath;
    }

    public VisualTank(VisualTank deepCopy, Tank logicalTank){
        texture = new Texture(deepCopy.texturePath);
        this.logicalTank = logicalTank;
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        this.texturePath = deepCopy.texturePath;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public float getRotation() {
        return logicalTank.getRotation();
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
        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(getRectangle(), logicalTank.getCoordinates().toGridPoint2(),
                logicalTank.getDestCoordinates().toGridPoint2(), logicalTank.getMotionProgress());
    }

    @Override
    public GameObject getLogicalEntity() {
        return logicalTank;
    }
}
