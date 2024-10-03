package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class VisualLevel{
    private final TiledMap levelTileMap;

    public VisualLevel(String levelTilePath) {
        levelTileMap = new TmxMapLoader().load(levelTilePath);
    }

    public TiledMap getLevelTileMap() {
        return levelTileMap;
    }

    void dispose() {
        levelTileMap.dispose();
    }

}