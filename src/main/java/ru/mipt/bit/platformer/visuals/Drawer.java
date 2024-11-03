package ru.mipt.bit.platformer.visuals;

import ru.mipt.bit.platformer.logics.LevelListener;
import ru.mipt.bit.platformer.logics.models.Level;

public interface Drawer extends LevelListener {
    // render visuals
    void drawVisuals(Level level);

    void dispose();
}
