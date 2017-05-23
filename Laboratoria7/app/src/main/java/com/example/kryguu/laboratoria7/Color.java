package com.example.kryguu.laboratoria7;

/**
 * Created by kryguu on 21.05.2017.
 */

public class Color {
    public int generateColor(int index, int maxIndex) { // generates color for specified index in table
        float h = 359 / maxIndex * index;
        float s = 100;
        float v = 100;

        float[] hsv = new float[] {h, s, v};

        return android.graphics.Color.HSVToColor(hsv);
    }
}
