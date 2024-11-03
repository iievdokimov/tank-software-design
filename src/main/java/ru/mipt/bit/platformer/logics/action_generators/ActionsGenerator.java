package ru.mipt.bit.platformer.logics.action_generators;

import ru.mipt.bit.platformer.logics.actions.Action;

import java.util.Collection;

public interface ActionsGenerator {
    Collection<Action> generate();
}
