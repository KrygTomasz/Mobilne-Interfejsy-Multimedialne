package com.example.kryguu.laboratoria8;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PainterActivity extends AppCompatActivity {

    private PainterView mPainterView;
    private GestureDetector mGestureDetector;
    private ArrayList<PointF> mPoints;

    private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener { // class to recognize double tap event
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            mPainterView.clear();
            return super.onDoubleTapEvent(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter);
        init();
    }

    private void init() { // initializes needed components
        initUIComponents();
        mGestureDetector = new GestureDetector(this, new SimpleGestureListener());
    }

    private void initUIComponents() { // inits user interface components
        mPainterView = (PainterView) findViewById(R.id.painterView);
        mPainterView.setOnTouchListener(getOnTouchListener());
        mPoints = new ArrayList<>();
        mPainterView.setPoints(mPoints);
    }

    private View.OnTouchListener getOnTouchListener() { // returns listener for touch gestures
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);

                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        mPoints.add(new PointF(motionEvent.getX(), motionEvent.getY()));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mPoints.add(new PointF(motionEvent.getX(), motionEvent.getY()));
                        break;
                    default:
                        break;
                }
                mPainterView.invalidate();
                return true;
            }
        };
        return listener;
    }
}
