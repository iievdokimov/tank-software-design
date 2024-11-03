package ru.mipt.bit.platformer.logics.actions;

import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Level;

public class CheckBulletStateAction implements Action{
    private final Level level;
    private final Bullet bullet;

    public CheckBulletStateAction(Bullet bullet, Level level) {
        this.level = level;
        this.bullet = bullet;
    }


    @Override
    synchronized public void process() {
        GameObject encounterObj = level.collisionWith(bullet);
        if(encounterObj != null){
            encounterObj.encounterBullet(bullet);
            level.removeBullet(bullet);
        }
    }
}
