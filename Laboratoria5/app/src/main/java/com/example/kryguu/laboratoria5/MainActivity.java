package com.example.kryguu.laboratoria5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private UpDownView upDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUIComponents();
    }

    private void initUIComponents() {
        upDownView = (UpDownView) findViewById(R.id.upDownView1);
    }
}
