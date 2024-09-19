package ru.mipt.bit.platformer.logics.tests;

import org.junit.Test;
import ru.mipt.bit.platformer.logics.Direction;
import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.Tank;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TankTest {
    private Vector2D getRandomVector2D(int max_value){
        int x = (int)(Math.random() * max_value);
        int y = (int)(Math.random() * max_value);
        return new Vector2D(x, y);
    }


    private ArrayList<Direction> getFourDirections(){
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(new Direction(Direction.simpleDirection.UP));
        directions.add(new Direction(Direction.simpleDirection.DOWN));
        directions.add(new Direction(Direction.simpleDirection.LEFT));
        directions.add(new Direction(Direction.simpleDirection.RIGHT));
        return directions;
    }

    @Test
    public void moveUP() {
        Level freeLevel = new Level();

        Vector2D coordinates = getRandomVector2D(100);
        Vector2D future_coordinates = new Vector2D(coordinates.x(),coordinates.y() + 1);
        Tank tank = new Tank(coordinates, Direction.simpleDirection.DOWN);

        tank.move(new Direction(Direction.simpleDirection.UP), freeLevel);

        assertEquals(tank.getDestCoordinates(), future_coordinates);
        assertEquals(tank.getCoordinates(), coordinates);
        assertEquals(tank.getDirection(), new Direction(Direction.simpleDirection.UP));
    }

    @Test
    public void move() {
        ArrayList<Direction> directions = getFourDirections();

        Level freeLevel = new Level();

        for (Direction direction : directions) {
            Vector2D coordinates = getRandomVector2D(100);
            Vector2D future_coordinates = coordinates.add(direction.getVector());
            Tank tank = new Tank(coordinates, Direction.simpleDirection.DOWN);

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
        Tank tank = new Tank(coordinates, Direction.simpleDirection.DOWN);

        Vector2D predict = tank.predictCoordinates(new Direction(Direction.simpleDirection.UP));

        assertEquals(predict, future_coordinates);
    }


    @Test
    public void makeTurn() {
        ArrayList<Direction> directions = getFourDirections();

        for (Direction direction : directions) {
            Vector2D coordinates = getRandomVector2D(100);
            Tank tank = new Tank(coordinates, Direction.simpleDirection.DOWN);

            tank.makeTurn(direction);
            assertEquals(tank.getDirection(), direction);
        }
    }

}