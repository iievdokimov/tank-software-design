package ru.mipt.bit.platformer.logics.action_generators;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.CheckBulletStateAction;
import ru.mipt.bit.platformer.logics.actions.MoveAction;
import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.Level;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class BulletActionsGenerator implements ActionsGenerator{
    Level level;
//    private final float actionGenerationPauseMs = 500;
//    private Instant timePrevGenerated;

    public BulletActionsGenerator(Level level) {
        this.level = level;
        //timePrevGenerated = Instant.now();
    }

    // TODO: limit frequency of generation
    @Override
    public Collection<Action> generate() {
        Collection<Action> actions = new ArrayList<>();

//        Instant timeNow = Instant.now();
//        if(Duration.between(timePrevGenerated, timeNow).toMillis() <= actionGenerationPauseMs){
//            return actions;
//        }else{
//            timePrevGenerated = timeNow;
//        }

        for (Bullet bullet: level.getBullets()) {
            actions.add(new MoveAction(bullet, level, bullet.getDirection()));
            //actions.add(new CheckBulletStateAction(bullet, level));
        };
        return actions;
    }
}
