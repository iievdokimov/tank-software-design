package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


import ru.mipt.bit.platformer.logics.ActionGenerator;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.input_controller.PlayerInput;
import ru.mipt.bit.platformer.logics.level_setup.FileLevelSetup;
import ru.mipt.bit.platformer.logics.level_setup.LevelSetup;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.visuals.*;

import java.util.Collection;

public class GameDesktopLauncher implements ApplicationListener {

    private Drawer drawer;

    private Level level;

    private ActionGenerator actionGenerator;


    @Override
    public void create() {
        LevelSetup levelSetup = new FileLevelSetup(
                "src/main/resources/levels/level1.txt");
        //LevelSetup levelSetup = new RandomLevelSetup();
        level = levelSetup.getLevel();

        actionGenerator = new ActionGenerator(level);

        drawer = new GdxDrawer(level);
    }

    @Override
    public void render() {
        clear_screen();

        Collection<Action> actions = actionGenerator.generate();
        actions.forEach(Action::process);

        level.updateProgress(Gdx.graphics.getDeltaTime());

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
