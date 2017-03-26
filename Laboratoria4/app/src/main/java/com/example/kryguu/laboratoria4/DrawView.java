package com.example.kryguu.laboratoria4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kryguu on 22.03.2017.
 */

public class DrawView extends View {

    private Paint m_paint;

    float m_x;
    float m_y;

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        m_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_paint.setColor(Color.BLUE);
    }

    public void setPos(float x, float y) {
        m_x = x;
        m_y = y;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawRectangle(canvas);
        drawBrokenLine(canvas);
        drawCurv(canvas);

    }

    private void drawCurv(Canvas canvas) {
        Path path = new Path();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
        path.moveTo(34, 259);
        path.cubicTo(68, 151, 286, 350, 336, 252);
        canvas.drawPath(path, paint);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(400,300,200,m_paint);
    }

    private void drawRectangle(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(50, 50, 300, 100, paint);
    }

    private void drawCurve(Canvas canvas) {

    }

    private void drawBrokenLine(Canvas canvas) {
        m_paint.setColor(Color.RED);
        m_paint.setStrokeWidth(5);
        m_paint.setStyle(Paint.Style.STROKE);
        Path myPath = new Path();
        myPath.moveTo(100,100);
        myPath.lineTo(200,200);
        myPath.lineTo(100,300);
        myPath.lineTo(200,400);
        myPath.lineTo(100,500);
        myPath.setLastPoint(200,600);

        canvas.drawPath(myPath,m_paint);
    }
}
