package ru.mipt.bit.platformer.logics.tests;

import ru.mipt.bit.platformer.logics.models.Direction;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.logics.models.Tank;
import ru.mipt.bit.platformer.util.Vector2D;

import org.junit.jupiter.api.Test; // JUnit 5 import

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TankTest {
    private Vector2D getRandomVector2D(int max_value){
        int x = (int)(Math.random() * max_value);
        int y = (int)(Math.random() * max_value);
        return new Vector2D(x, y);
    }


    private ArrayList<Direction> getFourDirections(){
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        return directions;
    }

    @Test
    public void moveUP() {
        Level freeLevel = new Level();

        Vector2D coordinates = getRandomVector2D(100);
        Vector2D future_coordinates = new Vector2D(coordinates.x(),coordinates.y() + 1);
        Tank tank = new Tank(coordinates, Direction.DOWN);

        tank.move(Direction.UP, freeLevel);

        assertEquals(tank.getDestCoordinates(), future_coordinates);
        assertEquals(tank.getCoordinates(), coordinates);
        assertEquals(tank.getDirection(), Direction.UP);
    }

    @Test
    public void move() {
        ArrayList<Direction> directions = getFourDirections();

        Level freeLevel = new Level();

        for (Direction direction : directions) {
            Vector2D coordinates = getRandomVector2D(100);
            Vector2D future_coordinates = coordinates.add(direction.getVector());
            Tank tank = new Tank(coordinates, Direction.DOWN);

            tank.move(direction, freeLevel);

            assertEquals(tank.getDestCoordinates(), future_coordinates);
            assertEquals(tank.getCoordinates(), coordinates);
            assertEquals(tank.getDirection(), direction);
        }
    }



    @Test
    public void predictCoordinatesUP() {
        Vector2D coordinates = getRandomVector2D(100);
        Vector2D future_coordinates = new Vector2D(coordinates.x(), coordinates.y() + 1);
        Tank tank = new Tank(coordinates, Direction.DOWN);

        Vector2D predict = tank.predictCoordinates(Direction.UP);

        assertEquals(predict, future_coordinates);
    }


    @Test
    public void makeTurn() {
        ArrayList<Direction> directions = getFourDirections();

        for (Direction direction : directions) {
            Vector2D coordinates = getRandomVector2D(100);
            Tank tank = new Tank(coordinates, Direction.DOWN);

            tank.makeTurn(direction);
            assertEquals(tank.getDirection(), direction);
        }
    }

}