package com.example.kryguu.laboratoria7;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private String mSensorName;
    private int mSensorType;
    TextView textView;
    PlotView plotView;

    private List<Signal> mSignals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initData();
        initSensorManager();
        initUIComponents();
        initPlot();
    }

    private void  initData() { // inits sensor from intent
        Intent intent = getIntent();
        mSensorName = intent.getStringExtra(InputExtras.SENSOR_NAME);
        mSensorType = intent.getIntExtra(InputExtras.SENSOR_TYPE, 0);
    }

    private void initUIComponents() { // inits user interface components
        setTitle(mSensorName);
        textView = (TextView) findViewById(R.id.textView);
        plotView = (PlotView) findViewById(R.id.plotView);
        plotView.setMax(mSensor.getMaximumRange());
    }

    private void initSensorManager() { // inits sensor manager
        mSignals = new ArrayList<>();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(mSensorType);
    }

    private void initPlot() { // inits plot view
        Log.d("MAXVALUE", String.valueOf(mSensor.getMaximumRange()));
        plotView.setMax(mSensor.getMaximumRange());
        plotView.setSignals(mSignals);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (mSignals.isEmpty()) {
            int numberOfValues = sensorEvent.values.length;
            Color color = new Color();

            for (int i = 0; i < numberOfValues; i++) {
                mSignals.add(new Signal(color.generateColor(i, numberOfValues), 100));
            }
        }

        String position = "";

        float[] values = sensorEvent.values;
        for (int i = 0; i < sensorEvent.values.length; i++) {
            position += String.format("%f \n",sensorEvent.values[i]);
            mSignals.get(i).add(sensorEvent.values[i]);
        }
        textView.setText(position);
        plotView.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
