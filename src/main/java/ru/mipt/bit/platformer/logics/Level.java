package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.ArrayList;
import java.util.List;


public class Level {
    private List<Tree> treeObstacles;

    private final int min_x;
    private final int min_y;
    private final int max_x;
    private final int max_y;

    public Level(Vector2D leftCorner, Vector2D rightCorner, List<Vector2D> obstCoordinates){
//        treeObstacles = new ArrayList<>();
        treeObstacles = new ArrayList<>(obstCoordinates.size());
        for (int i = 0; i < obstCoordinates.size(); i++) {
            treeObstacles.add(new Tree(obstCoordinates.get(i)));

        }

        min_x = (int)leftCorner.x();
        min_y = (int)leftCorner.y();
        max_x = (int)rightCorner.x();
        max_y = (int)rightCorner.y();

    }

    public Level(){
        treeObstacles = new ArrayList<>();
        min_x = Integer.MIN_VALUE;
        min_y = Integer.MIN_VALUE;
        max_x = Integer.MAX_VALUE;
        max_y = Integer.MAX_VALUE;
    }


    public List<Tree> getTreeObstacles() {
        return treeObstacles;
    }

    public boolean freeCoordinates(Vector2D coordinates) {
        if(!(coordinates.x() >= min_x && coordinates.x() <= max_x &&
            coordinates.y() >= min_y && coordinates.y() <= max_y)){
            return false;
        }

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
