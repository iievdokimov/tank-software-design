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
import ru.mipt.bit.platformer.logics.LevelListener;
import ru.mipt.bit.platformer.logics.models.*;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.Vector2D;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualBulletFactory;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualObjectFactoryRegistry;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualTankFactory;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualTreeFactory;

import java.util.ArrayList;
import java.util.Iterator;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GdxDrawer implements Drawer {
    private TiledMap gdxLevel;
    private VisualTank gdxTank;
    private VisualTree gdxTree;
    private VisualBullet gdxBullet;
    // TODO: array -> to hashSet
    private ArrayList<VisualObject> visualObjects;

    private MapRenderer levelRenderer;
    private TileMovement tileMovement;
    private Batch batch;
    private static TiledMapTileLayer groundLayer;

    private VisualObjectFactoryRegistry factoryRregistry;

    private HealthBarSettings healthBarSettings;

    private Level level;

    public GdxDrawer(Level level, HealthBarSettings healthBarSettings) {
        this.level = level;
        createVisuals(level);
        visualObjects = new ArrayList<>();
        batch = new SpriteBatch();
        levelRenderer = createSingleLayerMapRenderer(gdxLevel, batch);
        groundLayer = getSingleLayer(gdxLevel);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        factoryRregistry = new VisualObjectFactoryRegistry();
        factoryRregistry.registerFactory(Tank.class, new VisualTankFactory(gdxTank));
        factoryRregistry.registerFactory(Tree.class, new VisualTreeFactory(gdxTree));
        factoryRregistry.registerFactory(Bullet.class, new VisualBulletFactory(gdxBullet));

        this.healthBarSettings = healthBarSettings; //new HealthBarSettings(true);

        for (GameObject gameObject : level.getObjects()) {
            VisualObject visualObject = new VisualObjectHealthDecorator(factoryRregistry.createVisualObject(gameObject), this.healthBarSettings);
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

    private void createVisuals(Level level){
        gdxTank = new VisualTank("images/tank_blue.png", level.getPlayerTank());
        gdxTree = new VisualTree("images/greenTree.png", new Tree(new Vector2D()));
        gdxBullet = new VisualBullet("images/bullet.png", new Bullet(new Vector2D(), Direction.UP, 0, 0, level.getPlayerTank()));
        gdxLevel = new TmxMapLoader().load("level.tmx");
    }


    @Override
    synchronized public void onNewObject(GameObject object) {
        VisualObject newVisualObject = factoryRregistry.createVisualObject(object);
        visualObjects.add(newVisualObject);
    }

    @Override
    synchronized public void onDeleteObject(GameObject object) {
        Iterator<VisualObject> iterator = visualObjects.iterator();
        while (iterator.hasNext()) {
            VisualObject visualObject = iterator.next();
            if(visualObject.getLogicalEntity().equals(object)) {
                visualObject.dispose();
                iterator.remove();
            }
        }

    }
}
