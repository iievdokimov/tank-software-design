package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Tank;
import ru.mipt.bit.platformer.visuals.VisualObject;
import ru.mipt.bit.platformer.visuals.VisualTank;

public class VisualTankFactory implements VisualObjectFactory {
    private VisualTank gdxTank;

    public VisualTankFactory(VisualTank gdxTank) {
        this.gdxTank = gdxTank;
    }

    @Override
    public VisualObject createVisualObject(GameObject gameObject) {
        return new VisualTank(gdxTank, (Tank) gameObject);
    }
}
