package ru.mipt.bit.platformer.logics;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.input_controller.PlayerInput;
import ru.mipt.bit.platformer.logics.models.Level;

import java.util.ArrayList;

public class ActionGenerator {
    PlayerInput inputManager;

    public ActionGenerator(Level level){
        inputManager = new PlayerInput(level);
        // someAIGenerator initialization
    }

    public ArrayList<Action> generate(){
        ArrayList<Action> actions = new ArrayList<>();
        getUserActions(actions);
        generateTanksActions(actions);

        return actions;
    }

    private static void generateTanksActions(ArrayList<Action> actions) {
        // SomeAIGenerator.generateActions();
    }

    private void getUserActions(ArrayList<Action> actions){
        actions.add(inputManager.getAction());
    }
}
