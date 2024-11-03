package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.logics.models.Tank;

public class ShootAction implements Action{
    private final Tank tank;
    private final Level level;

    public ShootAction(Tank tank, Level level) {
        this.tank = tank;
        this.level = level;
    }

    @Override
    public void process() {
        tank.shoot(level);
    }
}
