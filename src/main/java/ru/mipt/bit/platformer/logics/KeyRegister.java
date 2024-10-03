package ru.mipt.bit.platformer.logics;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.logics.actions.Action;

import java.util.HashMap;

public class KeyRegister {
    private final HashMap<Input.Keys, Action> keyAction;

    public KeyRegister(Level level){
        keyAction = new HashMap<>();

    }

}
