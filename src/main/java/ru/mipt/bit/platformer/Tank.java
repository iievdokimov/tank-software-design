package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public interface Tank {

        void startMotion(Direction moveDirection);
        void stopMotion();

        public static final float motionStarted = 0f;
        public static final float motionFinished = 1f;

        void makeTurn(Direction turnDirection);

        float getMotionProgress();
        void updateMotionProgress(float deltaTime, float motionSpeed);

        GridPoint2 predictCoordinates(Direction moveDirection);
        GridPoint2 getCoordinates();
        GridPoint2 getDestCoordinates();
        float getRotation();


        // alternative: constants{90, -90, 180, 0} - better?\
        enum Direction{
                UP,
                DOWN,
                LEFT,
                RIGHT,
                NULL
        }

}
