package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.Direction;
import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.Tank;

public class MoveAction extends Action{
    private final Direction direction;
    private final Tank tank;
    private final Level level;



    public MoveAction(Tank linkTank, Direction direction, Level linkLevel){
        this.direction = direction;
        tank = linkTank;
        level = linkLevel;
    }

    @Override
    public void process() {
        tank.move(direction, level);
    }

    public Direction getDirection() {
        return direction;
    }
}
