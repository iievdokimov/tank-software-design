package ru.mipt.bit.platformer.logics.models;

import ru.mipt.bit.platformer.logics.LevelListener;
import ru.mipt.bit.platformer.util.Vector2D;

import java.util.*;
import java.util.stream.Stream;


public class Level {
    //private List<GameObject> gameObjects;
    private List<Tank> tanks;
    private List<Tree> trees;
    private Set<Bullet> bullets;
    private Tank playerTank;

    private final int min_x;
    private final int min_y;
    private final int max_x;
    private final int max_y;

    private List<LevelListener> subcsribers =  new ArrayList<>();

    public Level(Vector2D leftCorner, Vector2D rightCorner, List<Tank> tanks, List<Tree> trees, Tank playerTank){
        this.tanks = tanks;
        this.trees = trees;
        this.bullets = new HashSet<>();
        this.playerTank = playerTank;
        min_x = (int)leftCorner.x();
        min_y = (int)leftCorner.y();
        max_x = (int)rightCorner.x();
        max_y = (int)rightCorner.y();

        for(LevelListener subscriber : subcsribers) {
            for (GameObject object : getObjects()) {
                subscriber.onNewObject(object);
            }
        }
    }

    public Level(){
        tanks = new ArrayList<>();
        trees = new ArrayList<>();
        min_x = Integer.MIN_VALUE;
        min_y = Integer.MIN_VALUE;
        max_x = Integer.MAX_VALUE;
        max_y = Integer.MAX_VALUE;
    }

    public List<GameObject> getObjects() {
        //return Stream.concat(tanks.stream(), trees.stream()).toList().addAll(bullets.stream().toList());
        List<GameObject> merged = new ArrayList<>();
        merged.addAll(tanks);
        merged.addAll(trees);
        merged.addAll(bullets);
        return merged;
    }

    // TODO: -> use immutable wrapper for all getters
    public List<Tank> getTanks() {
        return tanks;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public Set<Bullet> getBullets(){
        return bullets;
    }

    synchronized public void addBullet(Bullet bullet){
        bullets.add(bullet);
        for(LevelListener subscriber : subcsribers){
            subscriber.onNewObject(bullet);
        }
    }

    public GameObject collisionWith(GameObject obj){
        Vector2D coordinates = obj.getCoordinates();
        if(!inBounds(coordinates)){
            return null;
        }
        for(GameObject obst : getObjects()){
            if(!obst.equals(obj) && obst.getCoordinates().equals(coordinates)){
                return obst;
            }
        }
        return null;
    }

    public boolean inBounds(Vector2D coordinates){
        return coordinates.x() >= min_x && coordinates.x() <= max_x &&
                coordinates.y() >= min_y && coordinates.y() <= max_y;
    }

    public boolean freeCoordinates(Vector2D coordinates) {
        if(!inBounds(coordinates)){
            return false;
        }

        boolean free = true;
        // check all tanks and tress, not bullets
        for (GameObject obst : Stream.concat(tanks.stream(), trees.stream()).toList()) {
            if(obst.getCoordinates().equals(coordinates) || obst.getDestCoordinates().equals(coordinates)) {
                free = false;
                break;
            }

        }
        return free;
    }

    synchronized public void updateProgress(float deltaTime){
        for (GameObject gameObject : getObjects()) {
            gameObject.updateProgress(deltaTime);
        }


        // also can be done action oriented
        Iterator<Tank> iterator = tanks.iterator();
        while (iterator.hasNext()) {
            Tank tank = iterator.next();
            if(tank.getRelativeHealth() <= 0){
                iterator.remove();
                for(LevelListener subscriber : subcsribers){
                    subscriber.onDeleteObject(tank);
                }
            }
            // TODO: playerTank's death -> gameOver
        }

        // instead of CheckBulletStateAction
        // (?) seems OCP-bad
        // but action-oriented code has костыли in the way i implement it
        //
//        Iterator<Bullet> iterator = bullets.iterator();
//        while (iterator.hasNext()) {
//            Bullet bullet = iterator.next();
//            GameObject encounterObj = collisionWith(bullet);
//            if (encounterObj != null && !encounterObj.equals(bullet.getShooter())) {
//                encounterObj.encounterBullet(bullet);
//                iterator.remove();
//                for(LevelListener subscriber : subcsribers){
//                    subscriber.onDeleteObject(bullet);
//                }
//            }
//        }
    }

    public Tank getPlayerTank(){
        return playerTank;
    }

    public void subscribe(LevelListener subscriber){
        subcsribers.add(subscriber);
    }

    public Vector2D getSize() {
        int width = max_x - min_x + 1;
        int height = max_y - min_y + 1;
        return new Vector2D(width, height);
    }

    synchronized public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
        for(LevelListener subscriber : subcsribers){
            subscriber.onDeleteObject(bullet);
        }
    }
}
