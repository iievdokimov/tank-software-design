package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.Objects;

public class Direction {

    private final simpleDirection direction;
    private final Vector2D rotation_vec;
    private final float rotation_angle;

    public Direction(simpleDirection startDirection) {
        direction = startDirection;
        switch (direction){
            case UP -> {
                rotation_vec = new Vector2D(0, 1);
                rotation_angle = 90f;
            }
            case DOWN -> {
                rotation_vec = new Vector2D(0, -1);
                rotation_angle = -90f;
            }
            case RIGHT -> {
                rotation_vec = new Vector2D(1, 0);
                rotation_angle = 0f;
            }
            case LEFT -> {
                rotation_vec = new Vector2D(-1, 0);
                rotation_angle = -180f;
            }
            default -> {
                rotation_angle = 0f;
                rotation_vec = new Vector2D(1, 0);
            }
        }
    }

    public enum simpleDirection{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    public Vector2D getVector(){
        return rotation_vec;
    }

    public float getAngle(){
        return rotation_angle;
    }

    public simpleDirection getSimpleDirection(){
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction1 = (Direction) o;
        return Float.compare(rotation_angle, direction1.rotation_angle) == 0 && direction == direction1.direction && Objects.equals(rotation_vec, direction1.rotation_vec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, rotation_vec, rotation_angle);
    }
}
