package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.models.Direction;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.logics.models.Tank;

public class MoveAction implements Action{
    private final Direction direction;
    private final Tank tank;
    private final Level level;



    public MoveAction(Tank tank, Level level, Direction direction){
        this.direction = direction;
        this.tank = tank;
        this.level = level;
    }

    @Override
    public void process() {
        tank.move(direction, level);
    }

    public Direction getDirection() {
        return direction;
    }
}
