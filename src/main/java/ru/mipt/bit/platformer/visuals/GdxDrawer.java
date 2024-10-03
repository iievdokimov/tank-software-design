package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logics.GameObject;
import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.Tank;
import ru.mipt.bit.platformer.logics.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GdxDrawer implements Drawer {
    private VisualLevel gdxLevel;
    //private VisualObject gdxTank;
    //private VisualObject gdxTree;
    private ArrayList<VisualObject> visualObjects;

    private MapRenderer levelRenderer;
    private TileMovement tileMovement;
    private Batch batch;
    private static TiledMapTileLayer groundLayer;

    public GdxDrawer(Level level, VisualLevel gdxLevel, VisualTree gdxTree, VisualTank gdxTank) {
        this.gdxLevel = gdxLevel;
        //this.gdxTank = gdxTank;
        //this.gdxTree = gdxTree;
        visualObjects = new ArrayList<>();
        batch = new SpriteBatch();
        levelRenderer = createSingleLayerMapRenderer(gdxLevel.getLevelTileMap(), batch);
        groundLayer = getSingleLayer(gdxLevel.getLevelTileMap());
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);


        for (GameObject gameObject : level.getObjects()) {
            VisualObject visualObject;
            if(gameObject.getClass() == Tank.class){
                visualObject = new VisualTank(gdxTank, (Tank) gameObject);
            } else if (gameObject.getClass() == Tree.class) {
                visualObject = new VisualTree(gdxTree, (Tree) gameObject);
            } else {
                // ?
                visualObject = new VisualTree(gdxTree, (Tree) gameObject);
            }

            visualObjects.add(visualObject);
            moveRectangleAtTileCenter(groundLayer, visualObject.getRectangle(), gameObject.getCoordinates().toGridPoint2());
        }

    }


    @Override
    public void drawVisuals(Level level) {
        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render game objects
        for (VisualObject visualObject : visualObjects) {
            visualObject.processMotion(tileMovement);
            visualObject.draw(batch);
        }

        // submit all drawing requests
        batch.end();


    }

    public void dispose(){
        // dispose visualObjects' textures
        for (VisualObject visualObject : visualObjects) {
            visualObject.dispose();
        }

        // dispose levelTile
        gdxLevel.dispose();

        batch.dispose();
    }

}
