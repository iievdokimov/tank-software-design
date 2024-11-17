package ru.mipt.bit.platformer.logics.action_generators;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.MoveAction;
import ru.mipt.bit.platformer.logics.actions.ShootAction;
import ru.mipt.bit.platformer.logics.models.Direction;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.logics.models.Tank;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AITanksActionsGenerator implements ActionsGenerator{
    private final Level level;

    private HashMap<Tank, Boolean> frameGeneratedAction;
    private final float actionGenerationPauseMs = 500;
    private final float tankShootingProbability = 20;
    private Instant timePrevGenerated;

    public AITanksActionsGenerator(Level level){
        this.level = level;
        timePrevGenerated = Instant.now();
//        for(Tank tank : level.getTanks()){
//            frameGeneratedAction.put(tank, false);
//        }
    }

    @Override
    public ArrayList<Action> generate() {
        ArrayList<Action> actions = new ArrayList<>();

        Instant timeNow = Instant.now();
        if(Duration.between(timePrevGenerated, timeNow).toMillis() <= actionGenerationPauseMs){
            return actions;
        }
        else{
            timePrevGenerated = timeNow;
        }

        List<Tank> tanks = level.getTanks();
        for (Tank tank : tanks) {
//            if(frameGeneratedAction.get(tank)){
//                continue;
//            }
            if(tank != level.getPlayerTank()){
                actions.add(new MoveAction(tank, level, getRandomDirection()));
                if(happensByProbability(tankShootingProbability)){
                    actions.add(new ShootAction(tank, level));
                }
                //frameGeneratedAction.put(tank, true);
            }
        }
        return actions;
    }

    private Direction getRandomDirection(){
        List<Direction> directions = List.of(Direction.UP, Direction.RIGHT, Direction.LEFT, Direction.DOWN);
        int idx = (int) (Math.random() * 100) % directions.size();
        return directions.get(idx);
    }

    private boolean happensByProbability(float probability){
        if(Math.random() * 100f <= probability){
            return true;
        }
        return false;
    }
}
