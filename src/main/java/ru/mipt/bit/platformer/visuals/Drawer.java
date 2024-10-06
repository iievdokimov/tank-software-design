package ru.mipt.bit.platformer.visuals;

import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.Tank;

public interface Drawer {
    // render visuals
    void drawVisuals(Level level);

    void dispose();
}
