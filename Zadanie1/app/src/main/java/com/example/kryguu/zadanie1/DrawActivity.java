package com.example.kryguu.zadanie1;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DrawActivity extends AppCompatActivity {

    DrawView drawView;
    String aStr;
    String bStr;
    String cStr;
    String x1Str;
    String x2Str;
    String y1Str;
    String y2Str;
    String xExtremumStr;
    String yExtremumStr;

    float a;
    float b;
    float c;
    Float x1;
    Float x2;
    Float y1;
    Float y2;
    ExtremumPoint extremumPoint = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        Intent intent = getIntent();
        aStr = intent.getStringExtra(IntentExtras.EXTRA_A);
        bStr = intent.getStringExtra(IntentExtras.EXTRA_B);
        cStr = intent.getStringExtra(IntentExtras.EXTRA_C);
        x1Str = intent.getStringExtra(IntentExtras.EXTRA_X1);
        x2Str = intent.getStringExtra(IntentExtras.EXTRA_X2);
        y1Str = intent.getStringExtra(IntentExtras.EXTRA_Y1);
        y2Str = intent.getStringExtra(IntentExtras.EXTRA_Y2);
        xExtremumStr = intent.getStringExtra(IntentExtras.EXTRA_EXTREMUM_X);
        yExtremumStr = intent.getStringExtra(IntentExtras.EXTRA_EXTREMUM_Y);
        if (aStr != null && bStr != null && cStr != null) {
            a = Float.parseFloat(aStr);
            b = Float.parseFloat(bStr);
            c = Float.parseFloat(cStr);
        }
        if (x1Str != null && x2Str != null && y1Str != null && y2Str != null) {
            x1 = Float.parseFloat(x1Str);
            x2 = Float.parseFloat(x2Str);
            y1 = Float.parseFloat(y1Str);
            y2 = Float.parseFloat(y2Str);
        }
        if (xExtremumStr != null && yExtremumStr != null) {
            boolean isMax;
            Float xExtremum = Float.parseFloat(xExtremumStr);
            Float yExtremum = Float.parseFloat(yExtremumStr);
            if (a > 0) {
                isMax = false;
            } else {
                isMax = true;
            }
            extremumPoint = new ExtremumPoint(xExtremum, yExtremum, isMax);
        } else {
            extremumPoint = null;
        }

        initUIComponents();
    }

    private void initUIComponents() { // initializes user interface components
        drawView = (DrawView) findViewById(R.id.drawView);
        drawView.init();
        passPointsToDrawView();
    }

    private void passPointsToDrawView() { // passes important points to draw
        float leftBorder, rightBorder;
        boolean solutionExists = false;
        if (x1 != null && x2 != null) {
            solutionExists = true;
            if (x1 > x2) {
                float tempX = x1;
                x1 = x2;
                x2 = tempX;
            }
            leftBorder = x1 - Math.abs(x1) - 10;
            rightBorder = x2 + Math.abs(x2) + 10;
        } else {
            if (a == 0) {
                leftBorder = -10;
                rightBorder = 10;
            } else {
                float x = extremumPoint.getX();
                leftBorder = x - Math.abs(x) - 10;
                rightBorder = x + Math.abs(x) + 10;
            }
        }
        Display display = getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        float dr = (rightBorder - leftBorder) / screenWidth;
        List<PointF> points = calculatePointsInRange(leftBorder, rightBorder, dr);

        drawView.setImportantPoints(points, extremumPoint, solutionExists);
    }

    private List<PointF> calculatePointsInRange(float r1, float r2, float dr) { // calculates points of function in given range with given shift
        List<PointF> points = new ArrayList();
        float range = r2 - r1;
        float y;

        if (dr < range) {
            for (float i = r1; i < r2; i+=dr) {
                y = calculateYFor(i);
                PointF p = new PointF(i,y);
                points.add(p);
            }
        }
        return points;
    }

    private float calculateYFor(float x) { // calculates function value for given x
        float y = a*x*x + b*x + c;
        return y;
    }

    private void onBackButtonClick() { // function which sets result and action to intent and closes activity

        super.onBackPressed();

    }

    @Override
    public void onBackPressed() { // function triggered when back button is pressed

        onBackButtonClick();

    }
}
