package com.example.kryguu.laboratoria5;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by kryguu on 05.04.2017.
 */

public class UpDownView extends LinearLayout {

    private ImageView mUpButton;
    private EditText mEditText;
    private ImageView mDownButton;

    private ButtonEvents mUpButtonEvents;
    private ButtonEvents mDownButtonEvents;

    private boolean mPressedFlag;
    private Handler mAutoUpdateHandler;
    private final int DELAY = 100;

    private int mButtonOrientation;
    private float mMin;
    private float mMax;
    private float mValue;
    private float mStep;

    public UpDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public UpDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.up_down_view, this, true);
            final TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.UpDownView);
            try {
                initUIComponents(a);
                setOnClickListeners();
                mAutoUpdateHandler = new Handler();
            }
            finally {
                a.recycle();
            }
    }

    private void initUIComponents(TypedArray a) {
        ViewGroup layout = (ViewGroup) getChildAt(0);
        mDownButton = (ImageButton) layout.getChildAt(0);
        mEditText = (EditText) layout.getChildAt(1);
        mUpButton = (ImageButton) layout.getChildAt(2);
        mButtonOrientation = a.getInteger(
                R.styleable.UpDownView_btnOrientation, 0);
        //...
        if (a.hasValue(R.styleable.UpDownView_btnDown)) {
            mDownButton.setBackground(
                    a.getDrawable(R.styleable.UpDownView_btnDown));
        }
        if (a.hasValue(R.styleable.UpDownView_editText)) {
            mEditText.setBackground(
                    a.getDrawable(R.styleable.UpDownView_editText));
        }
        if (a.hasValue(R.styleable.UpDownView_btnDown)) {
            mUpButton.setBackground(
                    a.getDrawable(R.styleable.UpDownView_btnUp));
        }
        mMin = a.getFloat(R.styleable.UpDownView_min, 0);
        mMax = a.getFloat(R.styleable.UpDownView_max, 100);
        mValue = a.getFloat(R.styleable.UpDownView_value, 0);
        mStep = a.getFloat(R.styleable.UpDownView_step, 1);

        mEditText.setText(Float.toString(mValue));
    }

    private void setOnClickListeners() {

        mUpButtonEvents = new ButtonEvents(mStep);
        mDownButtonEvents = new ButtonEvents(-mStep);

        mUpButton.setOnClickListener(mUpButtonEvents);
        mUpButton.setOnLongClickListener(mUpButtonEvents);
        mUpButton.setOnTouchListener(mUpButtonEvents);

        mDownButton.setOnClickListener(mDownButtonEvents);
        mDownButton.setOnLongClickListener(mDownButtonEvents);
        mDownButton.setOnTouchListener(mDownButtonEvents);

        mEditText.addTextChangedListener(new TextWatcher() { // mEditText listener
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float currentValue;
                try {
                    currentValue = Float.valueOf(charSequence.toString());
                } catch(NumberFormatException e) {
                    currentValue = mValue;
                }
                if (currentValue != mValue) {
                    checkValue(currentValue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void setValue(float value) { // set given value in EditText
        if (value >= mMin && value <= mMax) {
            mValue = value;
            mEditText.setText(Float.toString(mValue));
        } else {
            checkValue(value);
        }
    }

    private void checkValue(float value) { // checks if value is between min and max
        if (value > mMax) {
            mValue = mMax;
            mEditText.setText(Float.toString(mValue));
        } else if (value < mMin) {
            mValue = mMin;
            mEditText.setText(Float.toString(mValue));
        }
    }



    private class ButtonEvents implements OnClickListener, OnLongClickListener, OnTouchListener {

        private float mButtonStep;

        public ButtonEvents(float step) {
            mButtonStep = step;
        }

        @Override
        public void onClick(View view) {
            try {
                float currentValue = Float.valueOf(mEditText.getText().toString());
                float tempValue = currentValue + mButtonStep;
                setValue(tempValue);
            } catch (NumberFormatException e) { }
        }

        @Override
        public boolean onLongClick(View view) {
            mPressedFlag = true;
            try {
                class AutoUpdater implements Runnable {
                    public void run() {
                        if (mPressedFlag) {
                            mValue = Float.valueOf(mEditText.getText().toString());
                            float tempValue = mValue + mButtonStep;
                            setValue(tempValue);
                            mAutoUpdateHandler.postDelayed(new AutoUpdater(),DELAY);
                        }
                    }
                }
                mAutoUpdateHandler.post(new AutoUpdater());
            } catch (NumberFormatException e) { }
            return false;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                mPressedFlag = false;
            }
            return false;
        }
    }


    }
