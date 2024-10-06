package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.MoveAction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class KeyTools {
    public static void registerKeys(HashMap<Integer, Action> keyAction, Level level) {
        keyAction.put(UP, new MoveAction(level.getPlayerTank(), level, Direction.UP));
        keyAction.put(W, new MoveAction(level.getPlayerTank(), level, Direction.UP));
        keyAction.put(LEFT, new MoveAction(level.getPlayerTank(), level, Direction.LEFT));
        keyAction.put(A, new MoveAction(level.getPlayerTank(), level, Direction.LEFT));
        keyAction.put(DOWN, new MoveAction(level.getPlayerTank(), level, Direction.DOWN));
        keyAction.put(S, new MoveAction(level.getPlayerTank(), level, Direction.DOWN));
        keyAction.put(RIGHT, new MoveAction(level.getPlayerTank(), level, Direction.RIGHT));
        keyAction.put(D, new MoveAction(level.getPlayerTank(), level, Direction.RIGHT));

    }
}
