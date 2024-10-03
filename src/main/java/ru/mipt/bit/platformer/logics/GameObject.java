package ru.mipt.bit.platformer.logics;

import ru.mipt.bit.platformer.util.Vector2D;

public interface GameObject {
    void updateProgress(float deltaTime);

    float getRotation();

    Vector2D getCoordinates();

}
