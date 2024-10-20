package ru.mipt.bit.platformer.logics;

import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.models.Direction;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.logics.models.Tank;
import ru.mipt.bit.platformer.util.Vector2D;

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
        List<GameObject> objects = level.getObjects();
        for (GameObject object : objects) {
            // TODO: OCP is broken
            if(object.getClass() == Tank.class && object != level.getPlayerTank()){
                ((Tank) object).move(getRandomDirection(), level);
            }
        }

        return new ArrayList<>();
    }

    private Direction getRandomDirection(){
        List<Direction> directions = List.of(Direction.UP, Direction.RIGHT, Direction.LEFT, Direction.DOWN);
        int idx = (int) (Math.random() * 100) % directions.size();
        return directions.get(idx);
    }
}
