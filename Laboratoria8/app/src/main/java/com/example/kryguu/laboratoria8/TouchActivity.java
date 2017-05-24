package com.example.kryguu.laboratoria8;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

public class TouchActivity extends AppCompatActivity {

    private TextView mCountTV;
    private TextView mDownTV;
    private TextView mMoveTV;
    private TextView mUpTV;
    private TextView mPressureTV;

    private TextView mPointerDown1TV;
    private TextView mPointerDown2TV;
    private TextView mPointerDown3TV;
    private TextView mPointerDown4TV;
    private TextView mPointerDown5TV;

    private TextView mPointerUp1TV;
    private TextView mPointerUp2TV;
    private TextView mPointerUp3TV;
    private TextView mPointerUp4TV;
    private TextView mPointerUp5TV;

    private TextView[] mPointerDownTVs = new TextView[5];
    private TextView[] mPointerUpTVs = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        initUIComponents();
    }

    private void initUIComponents() { // inits user interface components
        mCountTV = (TextView) findViewById(R.id.countTV);
        mDownTV = (TextView) findViewById(R.id.downTV);
        mMoveTV = (TextView) findViewById(R.id.moveTV);
        mUpTV = (TextView) findViewById(R.id.upTV);
        mPressureTV = (TextView) findViewById(R.id.pressureTV);

        mPointerDownTVs[0] = (TextView) findViewById(R.id.pointerDown1TV);
        mPointerDownTVs[1] = (TextView) findViewById(R.id.pointerDown2TV);
        mPointerDownTVs[2] = (TextView) findViewById(R.id.pointerDown3TV);
        mPointerDownTVs[3] = (TextView) findViewById(R.id.pointerDown4TV);
        mPointerDownTVs[4] = (TextView) findViewById(R.id.pointerDown5TV);

        mPointerUpTVs[0] = (TextView) findViewById(R.id.pointerUp1TV);
        mPointerUpTVs[1] = (TextView) findViewById(R.id.pointerUp2TV);
        mPointerUpTVs[2] = (TextView) findViewById(R.id.pointerUp3TV);
        mPointerUpTVs[3] = (TextView) findViewById(R.id.pointerUp4TV);
        mPointerUpTVs[4] = (TextView) findViewById(R.id.pointerUp5TV);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int pointerID = event.getPointerId(pointerIndex);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mCountTV.setText(String.format("Count: %d",event.getPointerCount()));
                mPressureTV.setText(String.format("Pressure: %.2f",event.getPressure()));
                mDownTV.setText(String.format("(x,y): %.2f, %.2f",event.getX(),event.getY()));
                // obsługa Count, Pressure, Down (pointerIndex, pointerID, x, y)
                break;
            case MotionEvent.ACTION_UP:
                mCountTV.setText(String.format("Count: %d",event.getPointerCount()));
                mPressureTV.setText(String.format("Pressure: %.2f",event.getPressure()));
                mUpTV.setText(String.format("(x,y): %.2f, %.2f",event.getX(),event.getY()));
                // obsługa Count, Pressure, Up (pointerIndex, pointerID, x, y)
                break;
            case MotionEvent.ACTION_MOVE:
                mCountTV.setText(String.format("Count: %d",event.getPointerCount()));
                mPressureTV.setText(String.format("Pressure: %.2f",event.getPressure()));
                mMoveTV.setText(String.format("(x,y): %.2f, %.2f",event.getX(),event.getY()));
                // obsługa Count, Pressure, Move (pointerIndex, pointerID, x, y)
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mPointerDown1TV.setText(String.format("Count: %.2f",event.getX(0)));
                mPointerDown2TV.setText(String.format("Count: %.2f",event.getX(1)));
                mPointerDown3TV.setText(String.format("Count: %.2f",event.getX(2)));
                mPointerDown4TV.setText(String.format("Count: %.2f",event.getX(3)));
                mPointerDown5TV.setText(String.format("Count: %.2f",event.getX(4)));

                // obsługa Count, Pressure, PointerDown (pointerIndex, pointerID, x, y)
                // w zależności od pointerIndex
                break;
            case MotionEvent.ACTION_POINTER_UP:
                // obsługa Count, Pressure, PointerUp (pointerIndex, pointerID, x, y)
                // w zależności od pointerIndex
                break;
        }
        return super.onTouchEvent(event);
    }

}
