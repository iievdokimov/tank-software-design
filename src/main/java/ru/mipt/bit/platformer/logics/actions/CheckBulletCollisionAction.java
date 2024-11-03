package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Level;

import java.util.function.DoubleUnaryOperator;

public class CheckBulletCollisionAction implements Action{
    private final Level level;
    private final Bullet bullet;

    public CheckBulletCollisionAction(Bullet bullet, Level level) {
        this.level = level;
        this.bullet = bullet;
    }


    @Override
    synchronized public void process() {
        if(!bullet.isProcessed()){
            GameObject encounterObj = level.collisionWith(bullet);
            if(encounterObj != null && !bullet.getShooter().equals(encounterObj)){
                encounterObj.encounterBullet(bullet);
                level.removeBullet(bullet);
                bullet.onProcess();
                if(encounterObj.getClass() == Bullet.class){
                    level.removeBullet((Bullet) encounterObj);
                    ((Bullet)encounterObj).onProcess();
                }
            } else if (!level.inBounds(bullet.getCoordinates())) {
                level.removeBullet(bullet);
                bullet.onProcess();
            }
        }
    }
}
