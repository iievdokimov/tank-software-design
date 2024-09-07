package ru.mipt.bit.platformer;

public interface Drawer {
    // render visuals
    void drawVisuals(Level level, Tank tank);

    // calculate interpolated player screen coordinates
    void processTankMotion(Tank tank);

    void dispose();
}
