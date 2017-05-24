package com.example.kryguu.laboratoria8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PainterActivity extends AppCompatActivity {

    private PainterView mPainterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painter);
        initUIComponents();
    }

    private void initUIComponents() {
        mPainterView = (PainterView) findViewById(R.id.painterView);
    }
}
