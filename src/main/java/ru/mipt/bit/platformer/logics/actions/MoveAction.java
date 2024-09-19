package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.Direction;

public class MoveAction extends Action{
    private final Direction direction;

    public MoveAction(Direction direction){
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
