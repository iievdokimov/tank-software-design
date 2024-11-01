package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.util.TileMovement;

public interface VisualObject{
    Rectangle getRectangle();

    void draw(Batch batch);

    void dispose();

    void processMotion(TileMovement tileMovement);

    GameObject getLogicalEntity();

}