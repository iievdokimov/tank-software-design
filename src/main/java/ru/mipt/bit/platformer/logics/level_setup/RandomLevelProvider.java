package ru.mipt.bit.platformer.logics.level_setup;

import ru.mipt.bit.platformer.logics.models.*;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.*;
import java.util.stream.Stream;

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
        ArrayList<Tank> tanks = new ArrayList<>();
        ArrayList<Tree> trees = new ArrayList<>();
        Tank playerTank = createPlayerTank(tanks);

        HashSet<Vector2D> nonemptyCoordinates = new HashSet<>();
        nonemptyCoordinates.add(playerTank.getCoordinates());

        tanks = createTanks(tanks, nonemptyCoordinates);

        for(GameObject obj : tanks){
            nonemptyCoordinates.add(obj.getCoordinates());
        }

        trees = createTrees(nonemptyCoordinates);

        level = new Level(leftCorner, rightCorner, tanks, trees, playerTank);
        return level;
    }

    private ArrayList<Tank> createTanks(ArrayList<Tank> tanks, HashSet<Vector2D> nonemptyCoordinates){
        for (int i = 0; i < numTanks; i++) {
            tanks.add(new Tank(getRandomFreeCoordinate(nonemptyCoordinates), Direction.UP));
        }

        return tanks;
    }

    private ArrayList<Tree> createTrees(HashSet<Vector2D> nonemptyCoordinates){
        ArrayList<Tree> trees = new ArrayList<>();
        for (int x = 0; x <= rightCorner.x(); x++) {
            for (int y = 0; y <= rightCorner.y(); y++) {
                double coin = Math.random();
                if(coin <= obstDensity && !(nonemptyCoordinates.contains(new Vector2D(x, y)))){
                    trees.add(new Tree(new Vector2D(x, y)));
                }
            }
        }
        return trees;
    }

    private Tank createPlayerTank(ArrayList<Tank> tanks){
        Vector2D startCoordinates = getRandomVectorInField();
        Tank playerTank = new Tank(startCoordinates, Direction.UP);
        tanks.add(playerTank);
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
