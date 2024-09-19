package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.ArrayList;
import java.util.List;


public class Level {
    private List<Tree> treeObstacles;

    public Level(List<Vector2D> obstCoordinates){
//        treeObstacles = new ArrayList<>();
        treeObstacles = new ArrayList<>(obstCoordinates.size());
        for (int i = 0; i < obstCoordinates.size(); i++) {
            treeObstacles.add(new Tree(obstCoordinates.get(i)));

        }
    }

    public List<Tree> getTreeObstacles() {
        return treeObstacles;
    }

    public boolean freeCoordinates(Vector2D coordinates) {
        boolean free = true;
        for (Tree obst : treeObstacles) {
            if(obst.getCoordinates().equals(coordinates)){
                free = false;
                break;
            }
        }
        return free;
    }



}
