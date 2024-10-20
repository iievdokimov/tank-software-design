package ru.mipt.bit.platformer.logics.level_setup;

import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.util.Vector2D;

public interface LevelProvider {
    Level getLevel();
}
