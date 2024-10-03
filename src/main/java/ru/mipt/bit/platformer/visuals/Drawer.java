package ru.mipt.bit.platformer.visuals;

import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.Tank;

public interface Drawer {
    // render visuals
    void drawVisuals(Level level, Tank tank);

    // calculate interpolated player screen coordinates
    void processTankMotion(Tank tank);

    void dispose();
}
