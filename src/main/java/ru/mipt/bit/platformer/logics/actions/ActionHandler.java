package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.Tank;

public class ActionHandler {
    private final Tank linkPlayerTank;
    private final Level linkLevel;


    public ActionHandler(Tank playerTank, Level level){
        linkPlayerTank = playerTank;
        linkLevel = level;
    }


    public void handle(Action action){
        if(action instanceof MoveAction moveAction) {
            linkPlayerTank.move(moveAction.getDirection(), linkLevel);
        } else if (action instanceof ShootAction shootAction) {
            // process shooting
        } else if (action instanceof NoneAction none){
            // pass
        }

    }
}

