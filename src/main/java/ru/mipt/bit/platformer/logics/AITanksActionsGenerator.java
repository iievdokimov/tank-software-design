package ru.mipt.bit.platformer.logics;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.models.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AITanksActionsGenerator implements ActionsGenerator{
    private final Level level;

    public AITanksActionsGenerator(Level level){
        this.level = level;
    }

    @Override
    public ArrayList<Action> generate() {
        return new ArrayList<>();
    }
}
