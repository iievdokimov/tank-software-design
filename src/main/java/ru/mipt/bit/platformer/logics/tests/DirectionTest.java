package ru.mipt.bit.platformer.logics.tests;

import org.junit.Test;
import ru.mipt.bit.platformer.logics.Direction;
import ru.mipt.bit.platformer.util.Vector2D;

import static org.junit.Assert.*;

public class DirectionTest {

    @Test
    public void getVector() {
        Direction directionUP = new Direction(Direction.simpleDirection.UP);
        assertEquals(directionUP.getVector(), new Vector2D(0, 1));

        Direction directionDOWN = new Direction(Direction.simpleDirection.DOWN);
        assertEquals(directionDOWN.getVector(), new Vector2D(0, -1));

        Direction directionRIGHT = new Direction(Direction.simpleDirection.RIGHT);
        assertEquals(directionRIGHT.getVector(), new Vector2D(1, 0));

        Direction directionLEFT = new Direction(Direction.simpleDirection.LEFT);
        assertEquals(directionLEFT.getVector(), new Vector2D(-1, 0));
    }

    @Test
    public void getAngle() {
        float delta = 0.00001f;
        Direction directionUP = new Direction(Direction.simpleDirection.UP);
        assertEquals(directionUP.getAngle(), 90f, delta);

        Direction directionDOWN = new Direction(Direction.simpleDirection.DOWN);
        assertEquals(directionDOWN.getAngle(), -90f, delta);

        Direction directionRIGHT = new Direction(Direction.simpleDirection.RIGHT);
        assertEquals(directionRIGHT.getAngle(), 0f, delta);

        Direction directionLEFT = new Direction(Direction.simpleDirection.LEFT);
        assertEquals(directionLEFT.getAngle(), -180f, delta);
    }
}