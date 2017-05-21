package com.example.kryguu.laboratoria7;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private List<Sensor> mSensors;
    private ArrayAdapter<String> mAdapter;
    private List<String> mSensorNames;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSensorManager();
        initUIComponents();
        setOnListItemClick();
    }

    private void initUIComponents() { // inits user interface components
        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSensorNames);
        listView.setAdapter(mAdapter);
    }

    private void initSensorManager() { // inits all arrays from resources
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        mSensorNames = new ArrayList<String>();
        for (Sensor sensor: mSensors) {
            mSensorNames.add(sensor.getName());
        }
    }

    private void setOnListItemClick() { // sets onClick for list item
        listView.setOnItemClickListener(getItemClickListener());
    }

    private AdapterView.OnItemClickListener getItemClickListener() { // gets onClick for list item
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sensorName = adapterView.getItemAtPosition(i).toString();
                Log.d("asdasdasdasd", sensorName);
                Intent intent = new Intent(MainActivity.this, SensorActivity.class);
                intent.putExtra(InputExtras.SENSOR_NAME, sensorName);
                intent.putExtra(InputExtras.SENSOR_TYPE, mSensors.get(i).getType());
                startActivity(intent);
            }
        };
        return listener;
    }
}
