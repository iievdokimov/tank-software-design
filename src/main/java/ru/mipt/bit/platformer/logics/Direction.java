package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.Objects;

public enum Direction {

    UP(90f, new Vector2D(0, 1)),
    DOWN(-90f, new Vector2D(0, -1)),
    LEFT(180f, new Vector2D(-1, 0)),
    RIGHT(0f, new Vector2D(1, 0));


    private final Vector2D vector;
    private final float rotationAngle;

    private Direction(float rotationAngle, Vector2D vector) {
        this.rotationAngle = rotationAngle;
        this.vector = vector;
    }

    public enum simpleDirection{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    public Vector2D getVector(){
        return vector;
    }

    public float getAngle(){
        return rotationAngle;
    }

}
