package com.example.kryguu.zadanie1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import java.util.List;

/**
 * Created by kryguu on 22.03.2017.
 */


public class DrawView extends View {

    private Paint mFunctionPaint;
    private Paint mAxesPaint;

    float mCanvasWidth;
    float mCanvasHeight;

    float mXMin;
    float mXMax;
    float mYMin;
    float mYMax;

    List<PointF> m_points;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init() {
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        initPaints();
    }

    private void initPaints() { // initializes paints
        mFunctionPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFunctionPaint.setColor(Color.BLUE);
        mFunctionPaint.setStyle(Paint.Style.STROKE);
        mFunctionPaint.setStrokeWidth(3);
        mAxesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAxesPaint.setColor(Color.BLACK);
        mAxesPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mAxesPaint.setStrokeWidth(3);
    }

    public void setImportantPoints(List<PointF> points, ExtremumPoint extremumPoint, boolean solutionExists) { // sets important points to draw
        m_points = points;
        mXMin = points.get(0).x;
        mXMax = points.get(points.size() - 1).x;
        mYMin = mXMin;
        mYMax = mXMax;
        if (extremumPoint != null && solutionExists) {
            if (extremumPoint.isMaximum()) {
                mYMax = extremumPoint.getY() + 10;
                mYMin = 0 - 10;
            } else {
                mYMax = 0 + 10;
                mYMin = extremumPoint.getY() - 10;
            }
        } else if (extremumPoint != null && !solutionExists) {
            if (extremumPoint.isMaximum()) {
                mYMax = extremumPoint.getY() + 10;
                if (mYMax > 0) {
                    mYMin = -mYMax - 10;
                } else {
                    mYMin = mYMax + 10;
                }
            } else {
                mYMin = extremumPoint.getY() - 10;
                if (mYMin > 0) {
                    mYMax = mYMin + 10;
                } else {
                    mYMax = -mYMin - 10;
                }
            }
        } else {
            mYMax = mXMax;
            mYMin = mXMin;
        }
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawFunction(canvas);
        drawAxes(canvas);
    }

    private void drawFunction(Canvas canvas) { // draws function

        float tempX;
        float tempY;

        mCanvasWidth = canvas.getWidth();
        mCanvasHeight = canvas.getHeight();

        Path functionPath = new Path();

        tempX = calculateXPixelFrom(m_points.get(0).x);
        tempY = calculateYPixelFrom(m_points.get(0).y);
        functionPath.moveTo(tempX,tempY);
        for (int i = 1; i<m_points.size(); i++) {
            tempX = calculateXPixelFrom(m_points.get(i).x);
            tempY = calculateYPixelFrom(m_points.get(i).y);
            functionPath.lineTo(tempX,tempY);
        }
        canvas.drawPath(functionPath, mFunctionPaint);
    }

    private float calculateXPixelFrom(float x) { // calculates horizontal value of pixel for given x
        float xLength = mXMax - mXMin;
        float xPixel = ((x - mXMin)/xLength)*mCanvasWidth;
        return xPixel;
    }

    private float calculateYPixelFrom(float y) { // calculates vertical value of pixel for given y
        float yLength = mYMax - mYMin;
        float yPixel = ((y - mYMin)/yLength)*mCanvasWidth - mCanvasHeight;
        return -yPixel;
    }

    private void drawAxes(Canvas canvas) { // draws axes x and y
        float x0 = calculateXPixelFrom(0);
        float y0 = calculateYPixelFrom(0);

        Path axesPath = new Path();

        axesPath.moveTo(0, y0);
        axesPath.lineTo(mCanvasWidth, y0);

        axesPath.moveTo(x0, 0);
        axesPath.lineTo(x0, mCanvasHeight);

        canvas.drawPath(axesPath, mAxesPaint);
        mAxesPaint.setTextSize(50);


        if (x0 > mCanvasWidth/2) { // types on left side of axis
            canvas.drawText(StringTools.roundNumberToString(mYMin), x0 - 100, mCanvasHeight - 10, mAxesPaint);
            canvas.drawText(StringTools.roundNumberToString(mYMax), x0 - 100, 0 + 50, mAxesPaint);
        } else { // types on right side of axis
            canvas.drawText(StringTools.roundNumberToString(mYMin), x0, mCanvasHeight - 10, mAxesPaint);
            canvas.drawText(StringTools.roundNumberToString(mYMax), x0, 0 + 50, mAxesPaint);
        }




        canvas.drawText(StringTools.roundNumberToString(mXMin),0,y0,mAxesPaint);
        canvas.drawText(StringTools.roundNumberToString(mXMax),mCanvasWidth - 100, y0,mAxesPaint);

    }
}