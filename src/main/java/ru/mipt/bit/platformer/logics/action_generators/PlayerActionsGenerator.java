package ru.mipt.bit.platformer.logics.action_generators;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.NoneAction;
import ru.mipt.bit.platformer.logics.input_controller.KeyTools;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.visuals.HealthBarSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PlayerActionsGenerator implements ActionsGenerator {

    private final HashMap<Integer, Action> keyRegister;
    private final HashMap<Integer, Boolean> keyPressed;

    //public PlayerActionsGenerator(Level level, Drawer drawer) {
    public PlayerActionsGenerator(Level level, HealthBarSettings healthBarSettings) {
        keyRegister = new HashMap<>();
        KeyTools.registerKeys(keyRegister, level, healthBarSettings);

        keyPressed = new HashMap<>();
        for(Integer key : keyRegister.keySet()){
            keyPressed.put(key, false);
        }
    }

    @Override
    public ArrayList<Action> generate() {
        return new ArrayList<>(Collections.singletonList(getAction()));
    }

    public Action getAction() {
        Action result = null;
        // TODO: gdxInputProcessor
        for (Integer key : keyRegister.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                if(!keyPressed.get(key)) {
                    result = keyRegister.get(key);
                    keyPressed.put(key, true);
                    break;
                }
            }else {
                keyPressed.put(key, false);
            }
        }
        if (result != null) {
            return result;
        }

        return new NoneAction();
    }

}
