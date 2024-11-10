package ru.mipt.bit.platformer.logics;

import ru.mipt.bit.platformer.logics.actions.Action;

import java.util.ArrayList;
import java.util.Collection;

public interface ActionsGenerator {
    Collection<Action> generate();
}
