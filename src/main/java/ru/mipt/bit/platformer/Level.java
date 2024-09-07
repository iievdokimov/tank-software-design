package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class Level {

    private TiledMap levelTileMap;
    private MapRenderer levelRenderer;
    private TileMovement tileMovement;
    private Batch batch;

    private static TiledMapTileLayer groundLayer;
    //private static String textureObstaclePath;
    private TreeObstacle[] treeObstacles;


    public Level(String levelTilePath, Batch batch, GridPoint2[] obstCoordinates, String textureObstaclePath){
        levelTileMap = new TmxMapLoader().load(levelTilePath);
        levelRenderer = createSingleLayerMapRenderer(levelTileMap, batch);
        groundLayer = getSingleLayer(levelTileMap);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.batch = batch;

        treeObstacles = new TreeObstacle[obstCoordinates.length];
        for (int i = 0; i < obstCoordinates.length; i++) {
            treeObstacles[i] = new TreeObstacle(obstCoordinates[i], textureObstaclePath);
        }
    }

    public TreeObstacle[] getTreeObstacles() {
        return treeObstacles;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public MapRenderer getLevelRenderer() {
        return levelRenderer;
    }

    public void drawObstacles(){
        for (TreeObstacle treeObstacle : treeObstacles) {
            // render tree obstacle
            treeObstacle.draw(batch);
        }
    }

    public void dispose(){
        // dispose obstacles' textures
        for (TreeObstacle treeObstacle : treeObstacles) {
            treeObstacle.getTexture().dispose();
        }
        // dispose levelTile
        levelTileMap.dispose();
    }

    public static class TreeObstacle{
        private final GridPoint2 coordinates;
        private final Texture texture;
        private final TextureRegion graphics;
        private final Rectangle rectangle;
        private final float rotation;

        TreeObstacle(GridPoint2 coordinates, String texturePath){
            this.coordinates = new GridPoint2(coordinates);
            texture = new Texture(texturePath);
            graphics = new TextureRegion(texture);
            rectangle = createBoundingRectangle(graphics);
            moveRectangleAtTileCenter(groundLayer, rectangle, this.coordinates);
            rotation = 0f;
        }

        public void draw(Batch batch) {
            drawTextureRegionUnscaled(batch, graphics, rectangle, rotation);
        }

        public Rectangle getRectangle() {
            return rectangle;
        }

        public GridPoint2 getCoordinates() {
            return coordinates;
        }

        public Texture getTexture() {
            return texture;
        }

        public TextureRegion getGraphics() {
            return graphics;
        }
    }
}
