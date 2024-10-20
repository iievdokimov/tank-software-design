package ru.mipt.bit.platformer.logics.level_setup;

import ru.mipt.bit.platformer.logics.models.*;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.*;

public class RandomLevelProvider implements LevelProvider {
    private Level level;
    private final Vector2D leftCorner = new Vector2D(0, 0);
    private final Vector2D rightCorner = new Vector2D(9, 7);
    private final float obstDensity = 0.4f;

    // TODO:
    //public RandomLevelProvider( PARAMS ){}

    private Level configureRandomLevel(){
        // create game objects
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        Tank playerTank = createPlayerTank(gameObjects);

        //ArrayList<Vector2D> nonemptyCoordinates = new ArrayList<>(List.of(playerTank.getCoordinates()));
        HashSet<Vector2D> nonemptyCoordinates = new HashSet<>();
        nonemptyCoordinates.add(playerTank.getCoordinates());

        createTrees(gameObjects, nonemptyCoordinates);


        level = new Level(leftCorner, rightCorner, gameObjects, playerTank);
        return level;
    }

    private void createTrees(ArrayList<GameObject> gameObjects, HashSet<Vector2D> nonemptyCoordinates){
        for (int x = 0; x <= rightCorner.x(); x++) {
            for (int y = 0; y <= rightCorner.y(); y++) {
                double coin = Math.random();
                if(coin <= obstDensity && !(nonemptyCoordinates.contains(new Vector2D(x, y)))){
                    gameObjects.add(new Tree(new Vector2D(x, y)));
                }
            }
        }
    }

    private Tank createPlayerTank(ArrayList<GameObject> gameObjects){
        int x = (int)(Math.random() * rightCorner.x());
        int y = (int)(Math.random() * rightCorner.y());
        Vector2D startCoordinates = new Vector2D(x, y);
        Tank playerTank = new Tank(startCoordinates, Direction.UP);
        gameObjects.add(playerTank);
        return playerTank;
    }

    @Override
    public Level getLevel() {
        return configureRandomLevel();
    }
}
