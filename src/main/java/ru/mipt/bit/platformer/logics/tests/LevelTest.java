package ru.mipt.bit.platformer.logics.tests;

import org.junit.jupiter.api.Test; // JUnit 5 import
import ru.mipt.bit.platformer.logics.models.*;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {

    @Test
    public void freeCoordinatesObst() {
        Vector2D obstCoordinate = new Vector2D(1, 1);
        //ArrayList<GameObject> gameObjects = new ArrayList<>();
        ArrayList<Tank> tanks = new ArrayList<>();
        ArrayList<Tree> trees = new ArrayList<>();
        trees.add(new Tree(obstCoordinate));

        Vector2D leftCorner = new Vector2D(0, 0);
        Vector2D rightCorner = new Vector2D(10, 10);
        Level level = new Level(leftCorner, rightCorner, tanks, trees, new Tank(new Vector2D(-1, -1), Direction.RIGHT));

        Vector2D freePosition = new Vector2D(5, 6);
        Vector2D busyPosition = obstCoordinate;

        assertTrue(level.freeCoordinates(freePosition));
        assertFalse(level.freeCoordinates(busyPosition));
    }

    @Test
    public void freeCoordinatesBoarders() {
        //ArrayList<GameObject> gameObjects = new ArrayList<>();
        ArrayList<Tank> tanks = new ArrayList<>();
        ArrayList<Tree> trees = new ArrayList<>();

        int min_x = 0;
        int min_y = 0;
        int max_x = 10;
        int max_y = 10;

        Vector2D leftCorner = new Vector2D(min_x, min_y);
        Vector2D rightCorner = new Vector2D(max_x, max_y);
        Level level = new Level(leftCorner, rightCorner, tanks, trees, new Tank(new Vector2D(-2, -2), Direction.RIGHT));

        Vector2D freePosition = new Vector2D(getRandomNumber(min_x, max_x), getRandomNumber(min_y, max_y));
        Vector2D freePositionEdge = new Vector2D(min_x, getRandomNumber(min_y, max_y));

        Vector2D busyPosition = new Vector2D(min_x - 1, min_y - 1);
        Vector2D busyPositionUnderEdge = new Vector2D(min_x, min_y - 1);
        Vector2D busyPositionRightEdge = new Vector2D(max_x + 1, getRandomNumber(min_y, max_y));
        Vector2D busyPositionLeftEdge = new Vector2D(min_x - 1, getRandomNumber(min_y, max_y));
        Vector2D busyPositionUpperEdge = new Vector2D(getRandomNumber(min_x, max_x), max_y + 1);

        assertTrue(level.freeCoordinates(freePosition));
        assertTrue(level.freeCoordinates(leftCorner));
        assertTrue(level.freeCoordinates(rightCorner));
        assertTrue(level.freeCoordinates(freePositionEdge));

        assertFalse(level.freeCoordinates(busyPosition));
        assertFalse(level.freeCoordinates(busyPositionUnderEdge));
        assertFalse(level.freeCoordinates(busyPositionRightEdge));
        assertFalse(level.freeCoordinates(busyPositionLeftEdge));
        assertFalse(level.freeCoordinates(busyPositionUpperEdge));

    }

    private int getRandomNumber(int min, int max){
        return (int)(min + Math.random() * (max - min));
    }
}