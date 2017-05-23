package com.example.kryguu.laboratoria7;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by kryguu on 17.05.2017.
 */

public class PlotView extends View {

    private float mMax;
    private List<Signal> mSignals;
    private int mWidth;
    private int mHeight;
    private int mY0;

    public void setMax(float mMax) {
        this.mMax = mMax;
    }

    public void setSignals(List<Signal> signals) {
        this.mSignals = signals;
    }

    public PlotView(Context context) {
        super(context);
        initLayer();
    }

    public PlotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayer();
    }

    public PlotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayer();
    }

    private void initLayer() { // inits layer
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initParameters(canvas);
        drawHorizontalLine(canvas);
        if(mSignals != null) {
            for (Signal signal : mSignals) {
                float scale = mY0 / mMax;
                signal.draw(canvas, scale);
            }
        }
    }

    private void initParameters(Canvas canvas) { // inits width, height and y0 point
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        mY0 = mHeight / 2;
    }

    private void drawHorizontalLine(Canvas canvas) { // draws line for y = 0
        Path horizontalPath = new Path();

        horizontalPath.moveTo(0, mY0);
        horizontalPath.lineTo(mWidth, mY0);

        Paint paint = new Paint();
        paint.setColor(android.graphics.Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(horizontalPath, paint);
    }

}
