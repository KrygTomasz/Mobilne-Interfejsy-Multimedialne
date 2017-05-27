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
                setCount(event.getPointerCount());
                setPressure(event.getPressure());
                setDown(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                setCount(event.getPointerCount());
                setPressure(event.getPressure());
                setUp(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                setCount(event.getPointerCount());
                setPressure(event.getPressure());
                setMove(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                setPointerDown(pointerID, event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                setPointerUp(pointerID, event.getX(), event.getY());
                break;
        }
        return super.onTouchEvent(event);
    }

    private void setDown(float x, float y) { // sets touch down text field
        mDownTV.setText(String.format("(x,y): %.2f, %.2f", x, y));
    }

    private void setUp(float x, float y) { // sets touch up text field
        mUpTV.setText(String.format("(x,y): %.2f, %.2f", x, y));
    }

    private void setMove(float x, float y) { // sets touch move text field
        mMoveTV.setText(String.format("(x,y): %.2f, %.2f", x, y));
    }

    private void setPressure(float pressure) { // sets touch pressure text field
        mPressureTV.setText(String.format("Pressure: %.2f", pressure));
    }

    private void setCount(int count) { // sets touch count text field
        mCountTV.setText(String.format("Count: %d", count));
    }

    private void setPointerUp(int pointerID, float x, float y) { // sets pointer up text field depends on finger id
        if (pointerID < 5) {
            mPointerUpTVs[pointerID].setText(String.format("(x,y): %.2f, %.2f", x, y));
        }
    }

    private void setPointerDown(int pointerID, float x, float y) { // sets pointer down text field depends on finger id
        if (pointerID < 5) {
            mPointerDownTVs[pointerID].setText(String.format("(x,y): %.2f, %.2f", x, y));
        }
    }

}
