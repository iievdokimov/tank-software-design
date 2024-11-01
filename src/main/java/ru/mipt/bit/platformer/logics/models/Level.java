package ru.mipt.bit.platformer.logics.models;

import ru.mipt.bit.platformer.util.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Level {
    //private List<GameObject> gameObjects;
    private List<Tank> tanks;
    private List<Tree> trees;
    private Tank playerTank;

    private final int min_x;
    private final int min_y;
    private final int max_x;
    private final int max_y;

    //public Level(Vector2D leftCorner, Vector2D rightCorner, List<GameObject> gameObjects, Tank playerTank){
    public Level(Vector2D leftCorner, Vector2D rightCorner, List<Tank> tanks, List<Tree> trees, Tank playerTank){
        this.tanks = tanks;
        this.trees = trees;
        this.playerTank = playerTank;
        min_x = (int)leftCorner.x();
        min_y = (int)leftCorner.y();
        max_x = (int)rightCorner.x();
        max_y = (int)rightCorner.y();

    }

    public Level(){
        tanks = new ArrayList<>();
        trees = new ArrayList<>();
        min_x = Integer.MIN_VALUE;
        min_y = Integer.MIN_VALUE;
        max_x = Integer.MAX_VALUE;
        max_y = Integer.MAX_VALUE;
    }


    public List<GameObject> getObjects() {
        // TODO: -> use unmutable wrapper
        return Stream.concat(tanks.stream(), trees.stream()).toList();
    }

    public List<Tank> getTanks() {
        // TODO: -> use unmutable wrapper
        return tanks;
    }

    public List<Tree> getTrees() {
        // TODO: -> use unmutable wrapper
        return trees;
    }

    public boolean freeCoordinates(Vector2D coordinates) {
        if(!(coordinates.x() >= min_x && coordinates.x() <= max_x &&
            coordinates.y() >= min_y && coordinates.y() <= max_y)){
            return false;
        }

        boolean free = true;
        // now check all objects for collision (even player tank)
        // maybe will be changed
        for (GameObject obst : getObjects()) {
            if(obst.getCoordinates().equals(coordinates) || obst.getDestCoordinates().equals(coordinates)) {
                free = false;
                break;
            }

        }
        return free;
    }

    public void updateProgress(float deltaTime){
        for (GameObject gameObject : getObjects()) {
            gameObject.updateProgress(deltaTime);
        }
    }

    public Tank getPlayerTank(){
        return playerTank;
    }

    public Vector2D getSize() {
        int width = max_x - min_x + 1;
        int height = max_y - min_y + 1;
        return new Vector2D(width, height);
    }

}
