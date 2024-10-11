package ru.mipt.bit.platformer.logics.level_setup;

import ru.mipt.bit.platformer.logics.*;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.ArrayList;

public class FileLevelSetup implements LevelSetup {
    private Level level;

    public FileLevelSetup(String filePath){
        level = configureFromFile(filePath);
    }

    public Level getLevel(){
        return level;
    }

    public Level configureFromFile(String filePath){
        // create game objects
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(new Tree(new Vector2D(3, 3)));
        gameObjects.add(new Tree(new Vector2D(1, 3)));

        // create playerTank
        Vector2D startCoordinates = new Vector2D(1, 1);
        Tank playerTank = new Tank(startCoordinates, Direction.UP);
        gameObjects.add(playerTank);

        Vector2D leftCorner = new Vector2D(0, 0);
        Vector2D rightCorner = new Vector2D(9, 7);
        level = new Level(leftCorner, rightCorner, gameObjects, playerTank);

        return level;
    }

}
