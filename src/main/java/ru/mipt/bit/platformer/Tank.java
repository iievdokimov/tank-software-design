package ru.mipt.bit.platformer;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public interface Tank {
        void startMotion(Direction direction);
        void stopMotion();

        void makeTurn(Direction direction);

        float getMotionProgress();
        void updateMotionProgress(float deltaTime, float motionSpeed);

        //GridPoint2 predictCoordinates(Direction direction);
        GridPoint2 getCoordinates();
        GridPoint2 getDestCoordinates();

        Rectangle getRectangle();
        TextureRegion getGraphics();
        float getRotation();

        void dispose();

        enum Direction{
                UP,
                DOWN,
                LEFT,
                RIGHT
        }

}
