package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.models.Direction;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.logics.models.Movable;
import ru.mipt.bit.platformer.logics.models.Tank;

public class MoveAction implements Action{
    private final Direction direction;
    private final Movable obj;
    private final Level level;



    public MoveAction(Movable obj, Level level, Direction direction){
        this.direction = direction;
        this.obj = obj;
        this.level = level;
    }

    @Override
    public void process() {
        obj.move(direction, level);
    }

    public Direction getDirection() {
        return direction;
    }
}
