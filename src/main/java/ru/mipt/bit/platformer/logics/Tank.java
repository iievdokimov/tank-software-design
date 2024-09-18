package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.math.GridPoint2;


import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank {
    enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NULL
    }

    public static final float motionStarted = 0f;
    public static final float motionFinished = 1f;

    private float rotation;
    private GridPoint2 coordinates;
    private GridPoint2 destCoordinates;

    private float motionProgress;


    public Tank(GridPoint2 location, float rotationAngle) {
        coordinates = new GridPoint2(location);
        destCoordinates = new GridPoint2(coordinates);
        rotation = rotationAngle;
        motionProgress = motionFinished;
    }

    public void startMotion(Direction direction) {
        switch (direction){
            case UP -> destCoordinates.y++;
            case DOWN -> destCoordinates.y--;
            case RIGHT -> destCoordinates.x++;
            case LEFT -> destCoordinates.x--;
        }
        motionProgress = motionStarted;
    }

    public GridPoint2 predictCoordinates(Direction direction) {
        GridPoint2 predict = new GridPoint2(coordinates);
        switch (direction){
            case UP -> predict.y++;
            case DOWN -> predict.y--;
            case RIGHT -> predict.x++;
            case LEFT -> predict.x--;
        }
        return predict;
    }

    public void makeTurn(Direction direction) {
        switch (direction){
            case UP -> rotation = 90f;
            case DOWN -> rotation = -90;
            case RIGHT -> rotation = 0f;
            case LEFT -> rotation = -180f;
        }
    }

    public void updateMotionProgress(float deltaTime, float motionSpeed){
        motionProgress = continueProgress(motionProgress, deltaTime, motionSpeed);
        if (isEqual(motionProgress, motionFinished)) {
            coordinates.set(destCoordinates);
            stopMotion();
        }
    }

    public void stopMotion() {
        motionProgress = motionFinished;
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

    public float getRotation() {
        return rotation;
    }
}
