package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.MoveAction;
import ru.mipt.bit.platformer.logics.actions.NoneAction;

import static com.badlogic.gdx.Input.Keys.*;

public class PlayerInput {
    private final Tank linkPlayerTank;
    private final Level linkLevel;


    public PlayerInput(Tank playerTank, Level level){
        linkPlayerTank = playerTank;
        linkLevel = level;
    }

    public Action getAction(){
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new MoveAction(linkPlayerTank, Direction.UP, linkLevel);
        } else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new MoveAction(linkPlayerTank, Direction.LEFT, linkLevel);
        } else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new MoveAction(linkPlayerTank, Direction.DOWN, linkLevel);
        } else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new MoveAction(linkPlayerTank, Direction.RIGHT, linkLevel);
        }

        return new NoneAction();
    }
}
