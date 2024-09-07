package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;


public class Level {
    private TreeObstacle[] treeObstacles;

    public Level(GridPoint2[] obstCoordinates){
        treeObstacles = new TreeObstacle[obstCoordinates.length];
        for (int i = 0; i < obstCoordinates.length; i++) {
            treeObstacles[i] = new TreeObstacle(obstCoordinates[i]);
        }
    }

    public TreeObstacle[] getTreeObstacles() {
        return treeObstacles;
    }


    public static class TreeObstacle{
        private final GridPoint2 coordinates;
        private final float rotation;

        TreeObstacle(GridPoint2 coordinates){
            this.coordinates = new GridPoint2(coordinates);
            rotation = 0f;
        }

        public float getRotation() {
            return rotation;
        }

        public GridPoint2 getCoordinates() {
            return coordinates;
        }

    }
}
