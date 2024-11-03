package ru.mipt.bit.platformer.logics.models;

import ru.mipt.bit.platformer.util.Vector2D;

public interface GameObject {
    void updateProgress(float deltaTime);

    void encounterBullet(Bullet bullet);

    float getRotation();

    Vector2D getCoordinates();

    Vector2D getDestCoordinates();
}
