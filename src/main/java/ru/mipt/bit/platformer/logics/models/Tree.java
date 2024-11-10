package ru.mipt.bit.platformer.logics.models;

import ru.mipt.bit.platformer.util.Vector2D;

public class Tree implements GameObject{
    private final Vector2D coordinates;
    private final float rotation;

    public Tree(Vector2D coordinates){
        this.coordinates = new Vector2D(coordinates);
        rotation = 0f;
    }

    @Override
    public void updateProgress(float deltaTime) {
        // idle
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public Vector2D getCoordinates() {
        return coordinates;
    }

    @Override
    public Vector2D getDestCoordinates() {
        return coordinates;
    }

}