package com.example.kryguu.laboratoria5;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
                //...
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

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                float currentValue;
                try {
                    currentValue = Float.valueOf(editable.toString());
                } catch(NumberFormatException e) {
                    currentValue = mValue;
                }
                trySetValue(currentValue);
            }
        });

        mDownButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                float currentValue = Float.valueOf(mEditText.getText().toString());
                float tempValue = currentValue - mStep;
                trySetValue(tempValue);
            }
        });

        mUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                float currentValue = Float.valueOf(mEditText.getText().toString());
                float tempValue = currentValue + mStep;
                trySetValue(tempValue);
            }
        });

    }

    private void trySetValue(float value) {
        if (value >= mMin && value <= mMax) {
            mValue = value;
            mEditText.setText(Float.toString(mValue));
        } else if (value > mMax) {
            mValue = mMax;
            mEditText.setText(Float.toString(mValue));
        } else if (value < mMin) {
            mValue = mMin;
            mEditText.setText(Float.toString(mValue));
        }
    }


}
