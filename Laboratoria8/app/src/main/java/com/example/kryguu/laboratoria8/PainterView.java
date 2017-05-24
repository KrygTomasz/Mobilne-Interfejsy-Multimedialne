package com.example.kryguu.laboratoria8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by kryguu on 24.05.2017.
 */

public class PainterView extends View {

    final int RADIUS = 2;
    private Paint mPaint;
    private List<Float> mXs;
    private List<Float> mYs;

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

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(android.graphics.Color.BLACK);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDot(canvas, 100, 100);
    }

    private void drawDot(Canvas canvas, float x, float y) {
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }
}
