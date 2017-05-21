package com.example.kryguu.laboratoria7;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kryguu on 17.05.2017.
 */

public class PlotView extends View {

    private float mMax;

    public void setMax(float mMax) {
        this.mMax = mMax;
    }

    public float getMax() {
        return mMax;
    }

    public PlotView(Context context) {
        super(context);
    }

    public PlotView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
