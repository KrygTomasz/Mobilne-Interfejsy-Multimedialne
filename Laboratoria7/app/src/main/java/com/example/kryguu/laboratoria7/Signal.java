package com.example.kryguu.laboratoria7;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by kryguu on 17.05.2017.
 */

public class Signal {
    private Paint mPaint; 		// pędzel do prezentacji
    private ArrayList mValues; 	// lista z wartościami próbek sygnału
    private int mLimit; 		// maksymalna liczba próbek na liście

    public Signal(int color, int limit) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mLimit = limit;

        mValues = new ArrayList<>();
    }
    public void Add(float value) {
        if(mValues.size() == mLimit) {
            mValues.remove(0);
        }
        mValues.add(value);
    }
    public void Draw(Canvas canvas, float scale) {
// narysować przebieg sygnału z użyciem przygotowanego pędzla
// początek po lewej stronie ekranu
// wartość 0 w połowie wysokości „płótna”
// wartości próbek skalować z użyciem argumentu „scale”
    }
}
