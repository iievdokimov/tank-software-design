package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.math.GridPoint2;

public class Tree{
    private final GridPoint2 coordinates;
    private final float rotation;

    Tree(GridPoint2 coordinates){
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