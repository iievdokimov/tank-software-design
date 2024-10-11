package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


import ru.mipt.bit.platformer.logics.*;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.level_setup.FileLevelSetup;
import ru.mipt.bit.platformer.logics.level_setup.LevelSetup;
import ru.mipt.bit.platformer.visuals.*;

public class GameDesktopLauncher implements ApplicationListener {

    private Drawer drawer;

    private Level level;

    private PlayerInput inputManager;


    @Override
    public void create() {

        LevelSetup levelSetup = new FileLevelSetup("./resorces/level1.txt");
        level = levelSetup.getLevel();

        //actionHandler = new ActionHandler();
        inputManager = new PlayerInput(level);

        // create drawer
        drawer = new GdxDrawer(level);
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
