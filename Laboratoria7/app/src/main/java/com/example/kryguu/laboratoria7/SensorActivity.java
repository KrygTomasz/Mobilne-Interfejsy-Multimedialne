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

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private String mSensorName;
    private int mSensorType;
    TextView textView;
    PlotView plotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initData();
        initSensorManager();
        initUIComponents();
    }

    private void  initData() {
        Intent intent = getIntent();
        mSensorName = intent.getStringExtra(InputExtras.SENSOR_NAME);
        mSensorType = intent.getIntExtra(InputExtras.SENSOR_TYPE, 0);
    }

    private void initUIComponents() {
        setTitle(mSensorName);
        textView = (TextView) findViewById(R.id.textView);
        plotView = (PlotView) findViewById(R.id.plotView);
        plotView.setMax(mSensor.getMaximumRange());
    }

    private void initSensorManager() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(mSensorType);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String position = "";
        float[] values = sensorEvent.values;
        for (float value : values) {
            position += String.format("%f \n",value);
        }
        textView.setText(position);

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
