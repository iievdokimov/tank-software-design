package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


import ru.mipt.bit.platformer.logics.AITanksActionsGenerator;
import ru.mipt.bit.platformer.logics.ActionsGenerator;
import ru.mipt.bit.platformer.logics.PlayerActionsGenerator;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.level_setup.FileLevelProvider;
import ru.mipt.bit.platformer.logics.level_setup.LevelProvider;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.visuals.*;

import java.util.ArrayList;
import java.util.Collection;

public class GameDesktopLauncher implements ApplicationListener {

    private Drawer drawer;

    private LevelProvider levelProvider;

    private Level level;

    private ArrayList<ActionsGenerator> actionGenerators;


    public GameDesktopLauncher(LevelProvider levelProvider){
        super();
        this.levelProvider = levelProvider;
    }


    @Override
    public void create() {
        level = levelProvider.getLevel();

        actionGenerators = new ArrayList<>();
        actionGenerators.add(new PlayerActionsGenerator(level));
        actionGenerators.add(new AITanksActionsGenerator(level));

        drawer = new GdxDrawer(level);
    }

    @Override
    public void render() {
        clearScreen();

        Collection<Action> actions = new ArrayList<>();
        actionGenerators.forEach(generator -> actions.addAll(generator.generate()));
        actions.forEach(Action::process);

        level.updateProgress(Gdx.graphics.getDeltaTime());

        drawer.drawVisuals(level);

    }

    private static void clearScreen() {
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


//    class LevelWindowConfig{
//        public
//    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        LevelProvider levelProvider = new FileLevelProvider(
                "src/main/resources/levels/level1.txt");
        //LevelSetup levelSetup = new RandomLevelSetup();

        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);


        new Lwjgl3Application(new GameDesktopLauncher(levelProvider), config);
    }
}
