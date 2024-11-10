package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


import ru.mipt.bit.platformer.logics.action_generators.AITanksActionsGenerator;
import ru.mipt.bit.platformer.logics.action_generators.ActionsGenerator;
import ru.mipt.bit.platformer.logics.action_generators.BulletActionsGenerator;
import ru.mipt.bit.platformer.logics.action_generators.PlayerActionsGenerator;
import ru.mipt.bit.platformer.logics.actions.Action;
import ru.mipt.bit.platformer.logics.level_setup.LevelProvider;
import ru.mipt.bit.platformer.logics.level_setup.RandomLevelProvider;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.util.Vector2D;
import ru.mipt.bit.platformer.visuals.*;

import java.util.ArrayList;
import java.util.Collection;

public class GameDesktopLauncher implements ApplicationListener {

    private Drawer drawer;

    private Level level;

    private ArrayList<ActionsGenerator> actionGenerators;


    public GameDesktopLauncher(Level level){
        super();
        this.level = level;
    }


    @Override
    public void create() {

        // TODO: dependency injection needed
        HealthBarSettings healthBarSettings = new HealthBarSettings(true);

        actionGenerators = new ArrayList<>();
        actionGenerators.add(new PlayerActionsGenerator(level, healthBarSettings));
        actionGenerators.add(new AITanksActionsGenerator(level));
        actionGenerators.add(new BulletActionsGenerator(level));

        drawer = new GdxDrawer(level, healthBarSettings);
        level.subscribe(drawer);
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

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

//        LevelProvider levelProvider = new FileLevelProvider(
//                "src/main/resources/levels/level1.txt");

        // use dependency injection to move construction process
        LevelProvider levelProvider = new RandomLevelProvider(
                new Vector2D(0, 0),
                new Vector2D(7, 7),
                0.1f, 4
                );

        Level level = levelProvider.getLevel();

        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        Vector2D levelSize = level.getSize();
        int squareTileWidth = 128;
        config.setWindowedMode((int)(squareTileWidth * levelSize.x()), (int)(squareTileWidth * levelSize.y()));
        // TODO: generate new level.tmx files for bigger than 8x10 levels (?)

        new Lwjgl3Application(new GameDesktopLauncher(level), config);
    }
}
