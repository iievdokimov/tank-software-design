package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.visuals.VisualObject;

import java.util.HashMap;
import java.util.Map;

public class VisualObjectFactoryRegistry {
    private Map<Class<? extends GameObject>, VisualObjectFactory> factoryMap = new HashMap<>();

    public void registerFactory(Class<? extends GameObject> gameObjectClass, VisualObjectFactory factory) {
        factoryMap.put(gameObjectClass, factory);
    }

    public VisualObject createVisualObject(GameObject gameObject) {
        VisualObjectFactory factory = factoryMap.get(gameObject.getClass());
        if (factory != null) {
            return factory.createVisualObject(gameObject);
        }
        throw new IllegalArgumentException("No factory registered for " + gameObject.getClass());
    }
}