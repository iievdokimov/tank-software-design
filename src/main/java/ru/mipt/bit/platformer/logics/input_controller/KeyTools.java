package ru.mipt.bit.platformer.logics.input_controller;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.MoveAction;
import ru.mipt.bit.platformer.logics.actions.ShootAction;
import ru.mipt.bit.platformer.logics.models.Direction;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.visuals.HealthBarSettings;
import ru.mipt.bit.platformer.visuals.actions.ToggleShowHealthAction;

import java.util.HashMap;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class KeyTools {
    public static void registerKeys(HashMap<Integer, Action> keyAction, Level level, HealthBarSettings healthBarSettings) {
        keyAction.put(UP, new MoveAction(level.getPlayerTank(), level, Direction.UP));
        keyAction.put(W, new MoveAction(level.getPlayerTank(), level, Direction.UP));
        keyAction.put(LEFT, new MoveAction(level.getPlayerTank(), level, Direction.LEFT));
        keyAction.put(A, new MoveAction(level.getPlayerTank(), level, Direction.LEFT));
        keyAction.put(DOWN, new MoveAction(level.getPlayerTank(), level, Direction.DOWN));
        keyAction.put(S, new MoveAction(level.getPlayerTank(), level, Direction.DOWN));
        keyAction.put(RIGHT, new MoveAction(level.getPlayerTank(), level, Direction.RIGHT));
        keyAction.put(D, new MoveAction(level.getPlayerTank(), level, Direction.RIGHT));

        keyAction.put(L, new ToggleShowHealthAction(healthBarSettings));

        keyAction.put(SPACE, new ShootAction(level.getPlayerTank(), level));
    }
}
