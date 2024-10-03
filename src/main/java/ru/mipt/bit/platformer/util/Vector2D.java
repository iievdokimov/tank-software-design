package ru.mipt.bit.platformer.util;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Objects;

public class Vector2D {
    private final float x;
    private final float y;

    public Vector2D(){
        x = 0;
        y = 0;
    }

    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D arg){
        this.x = arg.x;
        this.y = arg.y;
    }




    public Vector2D add(Vector2D arg){
        return new Vector2D(this.x + arg.x, this.y + arg.y);
    }

    public Vector2D sub(Vector2D arg){
        return new Vector2D(this.x - arg.x, this.y - arg.y);
    }

    public float x(){
        return this.x;
    }

    public float y(){
        return this.y;
    }

    public GridPoint2 toGridPoint2(){
        return new GridPoint2((int)x, (int)y);
    }

    public boolean equals(Vector2D arg) {
        return this.x == arg.x && this.y == arg.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Float.compare(x, vector2D.x) == 0 && Float.compare(y, vector2D.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
