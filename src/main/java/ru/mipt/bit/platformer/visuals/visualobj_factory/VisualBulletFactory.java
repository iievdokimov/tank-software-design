package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.visuals.VisualBullet;
import ru.mipt.bit.platformer.visuals.VisualObject;

public class VisualBulletFactory implements VisualObjectFactory {
        private VisualBullet gdxBullet;

        public VisualBulletFactory(VisualBullet gdxBullet) {
            this.gdxBullet = gdxBullet;
        }

        @Override
        public VisualObject createVisualObject(GameObject gameObject) {
            return new VisualBullet(gdxBullet, (Bullet) gameObject);
        }
    }
