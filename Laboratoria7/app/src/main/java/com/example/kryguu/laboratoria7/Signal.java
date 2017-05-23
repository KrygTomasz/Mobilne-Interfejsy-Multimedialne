package com.example.kryguu.laboratoria7;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kryguu on 17.05.2017.
 */

public class Signal {
    private Paint mPaint;
    private List<Float> mValues;
    private int mLimit;

    public Signal(int color, int limit) {
        mPaint = configurePaint(color);
        mLimit = limit;

        mValues = new ArrayList<>();
    }

    private Paint configurePaint(int color) { // configures paint
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    public void add(float value) { // pushes next value
        if(mValues.size() == mLimit) {
            mValues.remove(0);
        }
        mValues.add(value);
    }

    public void draw(Canvas canvas, float scale) { // draws plot
        if (!mValues.isEmpty()) {
            int width = canvas.getWidth();
            int y0 = canvas.getHeight() / 2;
            Path path = new Path();

            path.moveTo(0, y0 - mValues.get(0) * scale);
            for (int i = 1; i < mValues.size(); i++) {
                path.lineTo(width / (mLimit - 1) * i, y0 - mValues.get(i) * scale);
            }
            canvas.drawPath(path, mPaint);
        }
    }
}
