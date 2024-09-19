package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.MoveAction;
import ru.mipt.bit.platformer.logics.actions.NoneAction;

import static com.badlogic.gdx.Input.Keys.*;

public class PlayerInput {

    public static Action getAction(){
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new MoveAction(new Direction(Direction.simpleDirection.UP));
        } else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new MoveAction(new Direction(Direction.simpleDirection.LEFT));
        } else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new MoveAction(new Direction(Direction.simpleDirection.DOWN));
        } else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new MoveAction(new Direction(Direction.simpleDirection.RIGHT));
        }

        return new NoneAction();
    }
}
