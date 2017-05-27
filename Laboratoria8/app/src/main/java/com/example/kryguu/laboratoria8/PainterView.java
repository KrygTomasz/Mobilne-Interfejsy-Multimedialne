package com.example.kryguu.laboratoria8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kryguu on 24.05.2017.
 */

public class PainterView extends View {

    final int RADIUS = 10;
    private Paint mPaint;
    private ArrayList<PointF> mPoints;

    public PainterView(Context context) {
        super(context);
        initLayer();
    }

    public PainterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayer();
    }

    public PainterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayer();
    }

    private void initLayer() { // inits layer
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        initPaint();
    }

    private void initPaint() { // inits paint's colors, strokes, etc.
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(android.graphics.Color.BLACK);
        mPaint.setStrokeWidth(RADIUS);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setPoints(ArrayList<PointF> points) { // sets points to draw
        mPoints = points;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (PointF point : mPoints) {
            drawDot(canvas, point);
        }
    }

    private void drawDot(Canvas canvas, PointF point) { // draws dot on specified position
        canvas.drawCircle(point.x, point.y, RADIUS, mPaint);
    }

    public void clear() { // clears view
        mPoints.clear();
        invalidate();
    }
}
