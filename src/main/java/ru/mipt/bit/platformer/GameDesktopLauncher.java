package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

import ru.mipt.bit.platformer.Tank;
import static ru.mipt.bit.platformer.Tank.motionFinished;
import ru.mipt.bit.platformer.TexturedTank;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private Level level;

    private Tank playerTank;


    @Override
    public void create() {
        batch = new SpriteBatch();

        // create level
        GridPoint2[] treeObstacleCoordinates = {new GridPoint2(3, 3), new GridPoint2(1, 3)};
        level = new Level("level.tmx", batch,
                treeObstacleCoordinates, "images/greenTree.png");

        // create playerTank
        GridPoint2 startCoordinates = new GridPoint2(1, 1);
        float startRotation = 0f;
        playerTank = new TexturedTank(startCoordinates, startRotation, "images/tank_blue.png");

    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        PlayerInput.Result input = PlayerInput.chosenDirection();
        if(input.moveKeyPressed){
            if (isEqual(playerTank.getMotionProgress(), motionFinished)) {
                GridPoint2 predict = playerTank.predictCoordinates(input.direction);
                boolean collision = false;
                for (Level.TreeObstacle obst : level.getTreeObstacles()) {
                    if(obst.getCoordinates().equals(predict)){
                        collision = true;
                        break;
                    }
                }
                if (!collision) {
                    playerTank.startMotion(input.direction);
                }

                playerTank.makeTurn(input.direction);
            }
        }


        // calculate interpolated player screen coordinates
        // move to Tank.processMotion(TileMovement) (?)
        level.getTileMovement().moveRectangleBetweenTileCenters(playerTank.getRectangle(), playerTank.getCoordinates(),
                playerTank.getDestCoordinates(), playerTank.getMotionProgress());

        playerTank.updateMotionProgress(deltaTime, MOVEMENT_SPEED);


        // render each tile of the level
        level.getLevelRenderer().render();

        // start recording all drawing commands
        batch.begin();

        // render player
        drawTextureRegionUnscaled(batch, playerTank.getGraphics(), playerTank.getRectangle(), playerTank.getRotation());

        // render obstacles
        level.drawObstacles();

        // submit all drawing requests
        batch.end();
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
        playerTank.dispose();
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
