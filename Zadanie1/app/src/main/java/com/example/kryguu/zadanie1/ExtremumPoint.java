package com.example.kryguu.zadanie1;

/**
 * Created by kryguu on 25.03.2017.
 */

public class ExtremumPoint {
    private Float x;
    private Float y;
    private boolean isMaximum;

    public ExtremumPoint(Float x, Float y, boolean isMaximum) {
        this.x = x;
        this.y = y;
        this.isMaximum = isMaximum;
    }

    public Float getX() { // getter for x
        return x;
    }

    public Float getY() { // getter for y
        return y;
    }

    public boolean isMaximum() { // getter for isMaximum
        return isMaximum;
    }
}
