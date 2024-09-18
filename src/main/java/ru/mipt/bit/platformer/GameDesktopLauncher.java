package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;

import static ru.mipt.bit.platformer.logics.Tank.motionFinished;

import ru.mipt.bit.platformer.logics.Level;
import ru.mipt.bit.platformer.logics.PlayerInput;
import ru.mipt.bit.platformer.logics.Tank;
import ru.mipt.bit.platformer.logics.Tree;
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


    @Override
    public void create() {
        // create level
        ArrayList<GridPoint2> treeObstacleCoordinates = new ArrayList<GridPoint2>();
        treeObstacleCoordinates.add(new GridPoint2(3, 3));
        treeObstacleCoordinates.add(new GridPoint2(1, 3));
        level = new Level(treeObstacleCoordinates);

        // create playerTank
        GridPoint2 startCoordinates = new GridPoint2(1, 1);
        float startRotation = 0f;
        playerTank = new Tank(startCoordinates, startRotation);

        // create visuals
        VisualObject visualTank = new VisualObject("images/tank_blue.png");
        VisualObject visualTree = new VisualObject("images/greenTree.png");
        VisualLevel visualLevel = new VisualLevel("level.tmx");

        // create drawer
        drawer = new GdxDrawer(level, playerTank, visualLevel, visualTree, visualTank);
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        PlayerInput.Result input = PlayerInput.chooseDirection();
        if(input.moveKeyPressed){
            if (isEqual(playerTank.getMotionProgress(), motionFinished)) {
                GridPoint2 predict = playerTank.predictCoordinates(input.direction);
                if (level.freeCoordinates(predict)) {
                    playerTank.startMotion(input.direction);
                }

                playerTank.makeTurn(input.direction);
            }
        }

        drawer.processTankMotion(playerTank);

        playerTank.updateMotionProgress(deltaTime, MOVEMENT_SPEED);

        drawer.drawVisuals(level, playerTank);


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
