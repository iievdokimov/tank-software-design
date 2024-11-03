package ru.mipt.bit.platformer.logics.models;

import ru.mipt.bit.platformer.util.Vector2D;

import java.util.Objects;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static com.badlogic.gdx.math.MathUtils.random;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements GameObject, Movable {
    private final float motionStarted = 0f;
    private final float motionFinished = 1f;

    GameObject shooter;

    // костыль( only for broken action-oriented CheckBulletStateAction::process
    private boolean processed = false;

    private Direction direction;
    private Vector2D coordinates;
    private Vector2D destCoordinates;

    private float motionProgress;

    private final float movementSpeed;

    private final float damage;


    public Bullet(Vector2D location, Direction direction, float damage, float movementSpeed, GameObject shooter) {
        coordinates = new Vector2D(location);
        destCoordinates = new Vector2D(coordinates);
        this.direction = direction;
        motionProgress = motionStarted;

        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.shooter = shooter;
    }

    public GameObject getShooter() {
        return shooter;
    }

    @Override
    public void updateProgress(float deltaTime) {
        updateMotionProgress(deltaTime);
    }

    @Override
    public void encounterBullet(Bullet bullet) {
        // relatively disappears
    }

    @Override
    public float getRotation() {
        return direction.getAngle();
    }

    @Override
    public Vector2D getCoordinates() {
        return coordinates;
    }

    @Override
    public Vector2D getDestCoordinates() {
        return destCoordinates;
    }

    @Override
    public void move(Direction direction, Level level){
        if (destCoordinates.equals(coordinates)) {
            destCoordinates = destCoordinates.add(direction.getVector());
            motionProgress = motionStarted;
        }
    }

    private void updateMotionProgress(float deltaTime){
        motionProgress = continueProgress(motionProgress, deltaTime, movementSpeed);
        if (isEqual(motionProgress, motionFinished)) {
            coordinates = destCoordinates;
            motionProgress = motionStarted;
        }
    }

    public float getMotionProgress() {
        return motionProgress;
    }

    public Direction getDirection() {
        return direction;
    }

    public float getDamage() {
        return damage;
    }

    public void onProcess(){
        processed = true;
    }

    public boolean isProcessed(){
        return processed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bullet bullet = (Bullet) o;
        return Float.compare(motionProgress, bullet.motionProgress) == 0 && Float.compare(movementSpeed, bullet.movementSpeed) == 0 && Float.compare(damage, bullet.damage) == 0 && direction == bullet.direction && Objects.equals(coordinates, bullet.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, coordinates, motionProgress, movementSpeed, damage);
    }
}
