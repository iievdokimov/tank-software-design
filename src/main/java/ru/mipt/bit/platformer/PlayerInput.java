package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.A;

public class PlayerInput {
    public static class Result{
        public final boolean moveKeyPressed;
        public final Tank.Direction direction;

        Result(boolean keyPressed, Tank.Direction result){
            moveKeyPressed = keyPressed;
            direction = result;
        }
    }

    public static Result chosenDirection(){
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new Result(true, Tank.Direction.UP);
        } else if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return new Result(true, Tank.Direction.LEFT);
        } else if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new Result(true, Tank.Direction.DOWN);
        } else if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new Result(true, Tank.Direction.RIGHT);
        }

        return new Result(false, Tank.Direction.NULL);
    }
}
