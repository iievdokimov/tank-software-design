package ru.mipt.bit.platformer.logics.action_generators;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.MoveAction;
import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BulletMovementGenerator implements ActionsGenerator{
    Level level;

    public BulletMovementGenerator(Level level) {
        this.level = level;
    }

    @Override
    public Collection<Action> generate() {
        Collection<Action> actions = new ArrayList<>();
        for (Bullet bullet: level.getBullets()) {
            actions.add(new MoveAction(bullet, level, bullet.getDirection()));
        };
        return actions;
    }
}
