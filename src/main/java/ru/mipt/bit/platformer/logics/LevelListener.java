package ru.mipt.bit.platformer.logics;

import ru.mipt.bit.platformer.logics.models.GameObject;

public interface LevelListener {
    void onNewObject(GameObject object);

    void onDeleteObject(GameObject object);
}
