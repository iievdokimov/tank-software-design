package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
import ru.mipt.bit.platformer.visuals.configuration.AppConfig;

import java.util.ArrayList;
import java.util.Collection;

public class GameDesktopLauncher implements ApplicationListener {

    private Drawer drawer;

    private Level level;

    private ArrayList<ActionsGenerator> actionGenerators;

    private final ApplicationContext context;


    public GameDesktopLauncher(ApplicationContext context){
        super();
        this.context = context;
    }


    @Override
    public void create() {
        LevelProvider levelProvider = context.getBean(LevelProvider.class);
        this.level = levelProvider.getLevel();

        HealthBarSettings healthBarSettings = context.getBean(HealthBarSettings.class);

        // context.registerBean(Drawer.class, () -> new GdxDrawer(level, healthBarSettings));

        // can't create drawer before levelProvider creates it (?)
        drawer = new GdxDrawer(level, healthBarSettings);
        level.subscribe(drawer);

        actionGenerators = new ArrayList<>();
        actionGenerators.add(new PlayerActionsGenerator(level, healthBarSettings));
        actionGenerators.add(new AITanksActionsGenerator(level));
        actionGenerators.add(new BulletActionsGenerator(level)); // -> to Level:updateProgress
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
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        Vector2D levelSize = context.getBean("levelSize", Vector2D.class);
        int squareTileWidth = context.getBean("squareTileWidth", int.class);
        config.setWindowedMode((int)(squareTileWidth * levelSize.x()), (int)(squareTileWidth * levelSize.y()));
        // TODO: generate new level.tmx files for bigger than 8x10 levels (?)

        new Lwjgl3Application(new GameDesktopLauncher(context), config);
    }
}
