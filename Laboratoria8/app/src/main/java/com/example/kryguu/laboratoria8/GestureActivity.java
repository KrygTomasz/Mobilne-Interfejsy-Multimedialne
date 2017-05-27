package com.example.kryguu.laboratoria8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class GestureActivity extends AppCompatActivity {

    private InfoFragment mDoubleTapF;
    private InfoFragment mDoubleTapEventF;
    private InfoFragment mDownF;
    private InfoFragment mFlingF;
    private InfoFragment mLongPressF;
    private InfoFragment mScrollF;
    private InfoFragment mShowPressF;
    private InfoFragment mSingleTapConfirmedF;
    private InfoFragment mSingleTapUpF;

    private GestureDetector mGestureDetector;

    private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener { // class to recognize tap events

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mDoubleTapF.SetInfo(String.format("(x,y): %.2f, %.2f",e.getX(),e.getY()));
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            mDoubleTapEventF.SetInfo(String.format("(x,y): %.2f, %.2f",e.getX(),e.getY()));
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            mDownF.SetInfo(String.format("(x,y): %.2f, %.2f",e.getX(),e.getY()));
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mFlingF.SetInfo(String.format("(x,y): %.2f, %.2f",e1.getX(),e1.getY()));
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mLongPressF.SetInfo(String.format("(x,y): %.2f, %.2f",e.getX(),e.getY()));
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mScrollF.SetInfo(String.format("(x,y): %.2f, %.2f",e1.getX(),e1.getY()));
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            mShowPressF.SetInfo(String.format("(x,y): %.2f, %.2f",e.getX(),e.getY()));
            super.onShowPress(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            mSingleTapConfirmedF.SetInfo(String.format("(x,y): %.2f, %.2f",e.getX(),e.getY()));
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            mSingleTapUpF.SetInfo(String.format("(x,y): %.2f, %.2f",e.getX(),e.getY()));
            return super.onSingleTapUp(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        initUIComponents();
    }

    private void initUIComponents() { // inits user interface components
        mDoubleTapF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.doubleTapF);
        mDoubleTapF.SetHeader("Double Tap");
        mDoubleTapEventF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.doubleTapEventF);
        mDoubleTapEventF.SetHeader("Double Tap Event");
        mDownF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.downF);
        mDownF.SetHeader("Down");
        mFlingF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.flingF);
        mFlingF.SetHeader("Fling");
        mLongPressF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.longPressF);
        mLongPressF.SetHeader("Long Press");
        mScrollF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.scrollF);
        mScrollF.SetHeader("Scroll");
        mShowPressF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.showPressF);
        mShowPressF.SetHeader("Show Press");
        mSingleTapConfirmedF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.singleTapConfirmedF);
        mSingleTapConfirmedF.SetHeader("Single Tap Confirmed");
        mSingleTapUpF = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.singleTapUpF);
        mSingleTapUpF.SetHeader("Single Tap Up");

        mGestureDetector = new GestureDetector(this, new SimpleGestureListener());
        findViewById(android.R.id.content).setOnTouchListener(new TouchListener());
    }

    private class TouchListener implements View.OnTouchListener { // class to recognize touch
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            mGestureDetector.onTouchEvent(event);
            return true;
        }
    }
}
