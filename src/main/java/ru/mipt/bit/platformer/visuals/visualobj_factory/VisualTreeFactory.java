package ru.mipt.bit.platformer.visuals.visualobj_factory;

import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Tree;
import ru.mipt.bit.platformer.visuals.VisualObject;
import ru.mipt.bit.platformer.visuals.VisualTree;

public class VisualTreeFactory implements VisualObjectFactory {
    private VisualTree gdxTree;

    public VisualTreeFactory(VisualTree gdxTree) {
        this.gdxTree = gdxTree;
    }

    @Override
    public VisualObject createVisualObject(GameObject gameObject) {
        return new VisualTree(gdxTree, (Tree) gameObject);
    }
}
