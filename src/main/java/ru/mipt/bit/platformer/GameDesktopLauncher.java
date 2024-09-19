package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


import ru.mipt.bit.platformer.logics.*;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.actions.ActionHandler;
import ru.mipt.bit.platformer.util.Vector2D;
import ru.mipt.bit.platformer.visuals.Drawer;
import ru.mipt.bit.platformer.visuals.GdxDrawer;
import ru.mipt.bit.platformer.visuals.VisualLevel;
import ru.mipt.bit.platformer.visuals.VisualObject;

import java.util.ArrayList;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f; // ?


    private Drawer drawer;

    private Level level;

    private Tank playerTank;

    private ActionHandler actionHandler;

    @Override
    public void create() {
        // create level
        ArrayList<Vector2D> treeObstacleCoordinates = new ArrayList<Vector2D>();
        treeObstacleCoordinates.add(new Vector2D(3, 3));
        treeObstacleCoordinates.add(new Vector2D(1, 3));

        Vector2D leftCorner = new Vector2D(0, 0);
        Vector2D rightCorner = new Vector2D(9, 7);
        level = new Level(leftCorner, rightCorner, treeObstacleCoordinates);

        // create playerTank
        Vector2D startCoordinates = new Vector2D(1, 1);
        playerTank = new Tank(startCoordinates, Direction.simpleDirection.UP);

        actionHandler = new ActionHandler(playerTank, level);

        // create visuals
        VisualObject visualTank = new VisualObject("images/tank_blue.png");
        VisualObject visualTree = new VisualObject("images/greenTree.png");
        VisualLevel visualLevel = new VisualLevel("level.tmx");

        // create drawer
        drawer = new GdxDrawer(level, playerTank, visualLevel, visualTree, visualTank);
    }

    @Override
    public void render() {
        clear_screen();

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();


        Action playerAction = PlayerInput.getAction();
        actionHandler.handle(playerAction);

        drawer.processTankMotion(playerTank);

        playerTank.updateMotionProgress(deltaTime, MOVEMENT_SPEED);

        drawer.drawVisuals(level, playerTank);


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
