package com.example.kryguu.laboratoria9;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class VibratorActivity extends AppCompatActivity {

    ToggleButton mToggleVibrationButton;
    Button mButton;
    Vibrator mVibrator;

    private long mLong = 500;
    private long mShort = 200;
    private long mPause = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);
        init();
        initUIComponents();
    }

    private void init() {
        initUIComponents();

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mToggleVibrationButton.setOnCheckedChangeListener(getCheckedListener());
        mButton.setOnClickListener(getOnClickListener());
    }

    private void initUIComponents() { // inits user interface components
        mToggleVibrationButton = (ToggleButton) findViewById(R.id.toggleVibrationButton);
        mButton = (Button) findViewById(R.id.button);
    }

    private CompoundButton.OnCheckedChangeListener getCheckedListener() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mVibrator.vibrate(3000);
                } else {
                    mVibrator.cancel();
                }
            }
        };
        return listener;
    }

    private View.OnClickListener getOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long[] pattern = { 0, mShort, mPause, mShort, mPause, mShort, mPause, mLong, mPause, mLong, mPause, mLong, mPause, mShort, mPause, mShort, mPause, mShort};
                mVibrator.vibrate(pattern , -1);
            }
        };
        return listener;
    }
}
