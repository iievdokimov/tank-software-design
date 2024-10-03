package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Vector2D;


import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank {
    private final float motionStarted = 0f;
    private final float motionFinished = 1f;

    private Direction direction;
    private Vector2D coordinates;
    private Vector2D destCoordinates;

    private float motionProgress;

    private static final float MOVEMENT_SPEED = 0.4f;

    public Tank(Vector2D location, Direction.simpleDirection rotationAngle) {
        coordinates = new Vector2D(location);
        destCoordinates = new Vector2D(coordinates);
        direction = new Direction(rotationAngle);
        motionProgress = motionFinished;
    }

    public void move(Direction direction, Level level){
        if (isEqual(getMotionProgress(), motionFinished)) {
            Vector2D predict = predictCoordinates(direction);
            if (level.freeCoordinates(predict)) {
                startMotion(direction);
            }
            makeTurn(direction);
        }

    }

    public void startMotion(Direction direction) {
        destCoordinates = destCoordinates.add(direction.getVector());
        motionProgress = motionStarted;
    }

    public Vector2D predictCoordinates(Direction direction) {
        Vector2D predict = new Vector2D(coordinates);
        return predict.add(direction.getVector());
    }

    public void makeTurn(Direction direction) {
        this.direction = direction;
    }

    public void updateMotionProgress(float deltaTime){
        motionProgress = continueProgress(motionProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(motionProgress, motionFinished)) {
            coordinates = destCoordinates;
            stopMotion();
        }
    }

    public void stopMotion() {
        motionProgress = motionFinished;
    }

    public Vector2D getCoordinates() {
        return coordinates;
    }

    public Vector2D getDestCoordinates() {
        return destCoordinates;
    }

    public float getMotionProgress() {
        return motionProgress;
    }

    public float getRotation() {
        return direction.getAngle();
    }

    public Direction getDirection() {
        return direction;
    }
}
