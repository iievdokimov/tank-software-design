package ru.mipt.bit.platformer;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Tank;

import java.awt.*;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class TexturedTank implements Tank {
    private float rotation;
    private GridPoint2 coordinates;
    private GridPoint2 destCoordinates;
    private float motionProgress;

    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;

    public TexturedTank(GridPoint2 location, float rotationAngle, String texturePath) {
        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        texture = new Texture(texturePath);
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);

        coordinates = new GridPoint2(location);
        destCoordinates = new GridPoint2(coordinates);
        rotation = rotationAngle;
        motionProgress = 1f;
    }

    public void startMotion(Direction direction) {
        switch (direction){
            case UP -> destCoordinates.y++;
            case DOWN -> destCoordinates.y--;
            case RIGHT -> destCoordinates.x++;
            case LEFT -> destCoordinates.x--;
        }
        motionProgress = 0f;
    }

    public void makeTurn(Direction direction) {
        switch (direction){
            case UP -> rotation = 90f;
            case DOWN -> rotation = -90;
            case RIGHT -> rotation = 0f;
            case LEFT -> rotation = -180f;
        }
    }

    public void stopMotion() {
        motionProgress = 1f;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    public GridPoint2 getDestCoordinates() {
        return destCoordinates;
    }

    public float getMotionProgress() {
        return motionProgress;
    }

    public void updateMotionProgress(float deltaTime, float motionSpeed){
        motionProgress = continueProgress(motionProgress, deltaTime, motionSpeed);
        if (isEqual(motionProgress, 1f)) {
            coordinates.set(destCoordinates);
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextureRegion getGraphics() {
        return graphics;
    }

    public float getRotation() {
        return rotation;
    }

    public void dispose() {
        texture.dispose();
    }
}
