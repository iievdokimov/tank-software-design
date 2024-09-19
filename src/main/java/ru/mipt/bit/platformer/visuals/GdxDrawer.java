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
import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.Tank;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GdxDrawer implements Drawer {
    private VisualLevel gdxLevel;
    private VisualObject gdxTank;
    private VisualObject gdxTree;
    private VisualObject[] gdxTrees;

    private MapRenderer levelRenderer;
    private TileMovement tileMovement;
    private Batch batch;
    private static TiledMapTileLayer groundLayer;

    public GdxDrawer(Level level, Tank tank, VisualLevel gdxLevel, VisualObject gdxTree, VisualObject gdxTank) {
        this.gdxLevel = gdxLevel;
        this.gdxTank = gdxTank;
        this.gdxTree = gdxTree;
        this.gdxTrees = new VisualObject[level.getTreeObstacles().size()];
        batch = new SpriteBatch();
        levelRenderer = createSingleLayerMapRenderer(gdxLevel.getLevelTileMap(), batch);
        groundLayer = getSingleLayer(gdxLevel.getLevelTileMap());
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        for (int i = 0; i < level.getTreeObstacles().size(); i++) {
            gdxTrees[i] = new VisualObject(gdxTree);
            moveRectangleAtTileCenter(groundLayer, gdxTrees[i].getRectangle(),
                    level.getTreeObstacles().get(i).getCoordinates().toGridPoint2());
        }

        moveRectangleAtTileCenter(groundLayer, gdxTank.getRectangle(), tank.getCoordinates().toGridPoint2());
    }


    @Override
    public void drawVisuals(Level level, Tank tank) {
        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render obstacles
        for (int i = 0; i < gdxTrees.length; i++) {
            // draw every tree with rotation=0f;
            drawTextureRegionUnscaled(batch, gdxTrees[i].getGraphics(), gdxTrees[i].getRectangle(), 0f);
            // drawTextureRegionUnscaled(batch, gdxTrees[i].getGraphics(), gdxTrees[i].getRectangle(), level.getTreeObstacles()[i].getRotation());
        }

        // render player
        // moveRectangleAtTileCenter(groundLayer, gdxTank.getRectangle(), tank.getCoordinates());
        drawTextureRegionUnscaled(batch, gdxTank.getGraphics(), gdxTank.getRectangle(), tank.getRotation());

        // submit all drawing requests
        batch.end();


    }

    @Override
    public void processTankMotion(Tank playerTank) {
        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(gdxTank.getRectangle(), playerTank.getCoordinates().toGridPoint2(),
                playerTank.getDestCoordinates().toGridPoint2(), playerTank.getMotionProgress());
    }

    public void dispose(){
        // dispose obstacles' textures
        gdxTree.dispose();
        for (VisualObject tree : gdxTrees) {
            tree.dispose();
        }

        // dispose playerTank texture
        gdxTank.dispose();

        // dispose levelTile
        gdxLevel.dispose();

        batch.dispose();
    }

}
