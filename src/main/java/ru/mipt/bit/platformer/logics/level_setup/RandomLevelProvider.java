package ru.mipt.bit.platformer.logics.level_setup;

import ru.mipt.bit.platformer.logics.models.*;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.*;

public class RandomLevelProvider implements LevelProvider {
    private Level level;
    private final Vector2D leftCorner;
    private final Vector2D rightCorner;
    private final float obstDensity;
    private final int numTanks;

    public RandomLevelProvider(Vector2D leftCorner, Vector2D rightCorner, float obstDensity, int numTanks){
        this.leftCorner = leftCorner;
        this.rightCorner = rightCorner;
        this.obstDensity = obstDensity;
        this.numTanks = numTanks;
        // TODO: check numTanks < level.numFields

    }

    private Level configureRandomLevel(){
        // create game objects
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        Tank playerTank = createPlayerTank(gameObjects);

        HashSet<Vector2D> nonemptyCoordinates = new HashSet<>();
        nonemptyCoordinates.add(playerTank.getCoordinates());

        gameObjects = createTanks(gameObjects, nonemptyCoordinates);

        for(GameObject obj : gameObjects){
            nonemptyCoordinates.add(obj.getCoordinates());
        }

        gameObjects = createTrees(gameObjects, nonemptyCoordinates);

        level = new Level(leftCorner, rightCorner, gameObjects, playerTank);
        return level;
    }

    private ArrayList<GameObject> createTanks(ArrayList<GameObject> gameObjects, HashSet<Vector2D> nonemptyCoordinates){
        for (int i = 0; i < numTanks; i++) {
            gameObjects.add(new Tank(getRandomFreeCoordinate(nonemptyCoordinates), Direction.UP));
        }

        return gameObjects;
    }

    private ArrayList<GameObject> createTrees(ArrayList<GameObject> gameObjects, HashSet<Vector2D> nonemptyCoordinates){
        for (int x = 0; x <= rightCorner.x(); x++) {
            for (int y = 0; y <= rightCorner.y(); y++) {
                double coin = Math.random();
                if(coin <= obstDensity && !(nonemptyCoordinates.contains(new Vector2D(x, y)))){
                    gameObjects.add(new Tree(new Vector2D(x, y)));
                }
            }
        }
        return gameObjects;
    }

    private Tank createPlayerTank(ArrayList<GameObject> gameObjects){

        Vector2D startCoordinates = getRandomVectorInField();
        Tank playerTank = new Tank(startCoordinates, Direction.UP);
        gameObjects.add(playerTank);
        return playerTank;
    }

    private Vector2D getRandomVectorInField(){
        int x = (int)leftCorner.x() + (int)(Math.random() * (rightCorner.x() - leftCorner.x()));
        int y = (int)leftCorner.y() + (int)(Math.random() * (rightCorner.y() - leftCorner.y()));
        return new Vector2D(x, y);
    }

    private Vector2D getRandomFreeCoordinate(HashSet<Vector2D> nonemptyCoordinates){
        Vector2D result = getRandomVectorInField();
//        if(nonemptyCoordinates.size() == (int) (rightCorner.x() - leftCorner.x()) * (rightCorner.y() - leftCorner.y())){
//            throw
//        }
        while(nonemptyCoordinates.contains(result)){
            result = getRandomVectorInField();
        }

        return result;
    }

    @Override
    public Level getLevel() {
        return configureRandomLevel();
    }
}
