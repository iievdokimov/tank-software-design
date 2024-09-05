package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.Tank;


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
        motionProgress = motionFinished;
    }

    @Override
    public void startMotion(Direction direction) {
        switch (direction){
            case UP -> destCoordinates.y++;
            case DOWN -> destCoordinates.y--;
            case RIGHT -> destCoordinates.x++;
            case LEFT -> destCoordinates.x--;
        }
        motionProgress = motionStarted;
    }

    @Override
    public GridPoint2 predictCoordinates(Direction direction) {
        GridPoint2 predict = new GridPoint2(coordinates.x, coordinates.y);
        switch (direction){
            case UP -> predict.y++;
            case DOWN -> predict.y--;
            case RIGHT -> predict.x++;
            case LEFT -> predict.x--;
        }
        return predict;
    }

    @Override
    public void makeTurn(Direction direction) {
        switch (direction){
            case UP -> rotation = 90f;
            case DOWN -> rotation = -90;
            case RIGHT -> rotation = 0f;
            case LEFT -> rotation = -180f;
        }
    }

    @Override
    public void stopMotion() {
        motionProgress = motionFinished;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    @Override
    public GridPoint2 getDestCoordinates() {
        return destCoordinates;
    }
    @Override
    public float getMotionProgress() {
        return motionProgress;
    }

    @Override
    public void updateMotionProgress(float deltaTime, float motionSpeed){
        motionProgress = continueProgress(motionProgress, deltaTime, motionSpeed);
        if (isEqual(motionProgress, motionFinished)) {
            coordinates.set(destCoordinates);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public TextureRegion getGraphics() {
        return graphics;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
