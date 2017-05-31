package com.example.kryguu.laboratoria9;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class CameraActivity extends AppCompatActivity {

    Camera mCamera;
    CameraPreview mPreview;
    SurfaceView mSurface;
    SeekBar mSeekBar;
    ToggleButton mToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initUIComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        releaseCameraAndPreview();
        getCamera();
        mPreview.setCamera(mCamera);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCameraAndPreview();
    }

    private void initUIComponents() {
        getCamera();
        mSurface = (SurfaceView)findViewById(R.id.binarizedView);
        mPreview = new CameraPreview(this, mCamera, mSurface);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        mSeekBar = (SeekBar)findViewById(R.id.seekBar);
        mToggleButton = (ToggleButton)findViewById(R.id.toggleButton);

        mSeekBar.setMax(255);
        mSeekBar.setProgress(128);

        mSeekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
        mToggleButton.setOnCheckedChangeListener(new LightChangeListener());
    }

    private void getCamera() {
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            Log.d("CAMERA", "Error starting camera preview: " + e.getMessage());
        }
    }

    private void releaseCameraAndPreview() {
        mPreview.setCamera(null);
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            mPreview.SetThreshold(progress);
        }

        public void onStartTrackingTouch(SeekBar seekBar)
        {
        }

        public void onStopTrackingTouch(SeekBar seekBar)
        {
        }
    }

    private class LightChangeListener implements CompoundButton.OnCheckedChangeListener {

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if (mCamera!=null) {
                Camera.Parameters params = mCamera.getParameters();
                params.setFlashMode(isChecked?Camera.Parameters.FLASH_MODE_TORCH:Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(params);
            }
        }
    }
}
