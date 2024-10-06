package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.MoveAction;
import ru.mipt.bit.platformer.logics.actions.NoneAction;

import static com.badlogic.gdx.Input.Keys.*;

public class PlayerInput {
//    private final Tank linkPlayerTank;
//    private final Level linkLevel;
    private final KeyRegister keyRegister;

    public PlayerInput(Level level){
        keyRegister = new KeyRegister(level);
    }

    public Action getAction(){
        Action result = null;
        for(Integer key : keyRegister.getAllRegisteredKeys()) {
            if (Gdx.input.isKeyPressed(key)) {
                result = keyRegister.get(key);
                break;
            }
        }
        if(result != null){
            return result;
        }

        return new NoneAction();
    }
}
