package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.logics.*;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.util.Vector2D;
import ru.mipt.bit.platformer.visuals.*;

import java.util.ArrayList;

public class GameDesktopLauncher implements ApplicationListener {

    private Drawer drawer;

    private Level level;

    private Tank playerTank;

    private PlayerInput inputManager;


    @Override
    public void create() {
        // create game objects
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(new Tree(new Vector2D(3, 3)));
        gameObjects.add(new Tree(new Vector2D(1, 3)));

        // create playerTank
        Vector2D startCoordinates = new Vector2D(1, 1);
        playerTank = new Tank(startCoordinates, Direction.UP);
        gameObjects.add(playerTank);

        Vector2D leftCorner = new Vector2D(0, 0);
        Vector2D rightCorner = new Vector2D(9, 7);
        level = new Level(leftCorner, rightCorner, gameObjects, playerTank);


        //actionHandler = new ActionHandler();
        inputManager = new PlayerInput(level);

        // create visuals
        VisualTank visualTank = new VisualTank("images/tank_blue.png", playerTank);
        VisualTree visualTree = new VisualTree("images/greenTree.png", new Tree(new Vector2D()));
        TiledMap levelTileMap = new TmxMapLoader().load("level.tmx");

        // create drawer
        drawer = new GdxDrawer(level, levelTileMap, visualTree, visualTank);
    }

    @Override
    public void render() {
        clear_screen();

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        Action playerAction = inputManager.getAction();
        playerAction.process();

        level.updateProgress(deltaTime);

        drawer.drawVisuals(level);


    }

    private static void clear_screen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        drawer.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
