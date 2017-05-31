package com.example.kryguu.laboratoria9;

/**
 * Created by kryguu on 31.05.2017.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Log;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.hardware.Camera;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Camera.Size mCameraSize;
    private int mFrameSize;
    private byte[] mData;
    private ProcessPreview mProcessor;
    private volatile int mThreshold;
    private SurfaceView mSurface;
    private Activity mActivity;
    private volatile long mStartCapture;

    public CameraPreview(Activity activity, Camera camera, SurfaceView surface) {
        super(activity);
        mActivity = activity;
        mCamera = camera;
        mSurface = surface;
        mThreshold = 128;

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {

        if (mCamera == null) { return; }

        try {
            Camera.Parameters params = mCamera.getParameters();
            List<Camera.Size> sizes = params.getSupportedPreviewSizes();

            params.setColorEffect(Camera.Parameters.EFFECT_MONO);
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);

            // wybór rozmiaru obrazu (są uporządkowane od największego)
            mCameraSize = sizes.get(sizes.size()-1);
            params.setPreviewSize(mCameraSize.width, mCameraSize.height);
            mFrameSize = mCameraSize.width * mCameraSize.height;
            mData = new byte[mFrameSize * ImageFormat.getBitsPerPixel(params.getPreviewFormat())/8];
            mCamera.setParameters(params);

            mProcessor = new ProcessPreview();
            mCamera.addCallbackBuffer(mData);
            mCamera.setPreviewDisplay(holder);
            mCamera.setPreviewCallbackWithBuffer(mProcessor);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("PREVIEW", "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

        if (mHolder.getSurface() == null)
        {
            return;
        }

        try {
            mCamera.stopPreview();
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setPreviewCallbackWithBuffer(mProcessor);
            mCamera.startPreview();
        } catch (Exception e){
            Log.d("PREVIEW", "Error starting camera preview: " + e.getMessage());
        }
    }

    public void setCamera(Camera camera) {
        if (mCamera == camera) { return; }

        stopPreviewAndFreeCamera();

        mCamera = camera;

        if (mCamera != null) {

            requestLayout();

            try {
                mCamera.setPreviewDisplay(mHolder);
            } catch (IOException e) {
                Log.d("PREVIEW", "Error setting camera: " + e.getMessage());
            }

            mCamera.startPreview();
        }
    }

    public void SetThreshold(int threshold) {
        mThreshold = threshold;
    }

    private void stopPreviewAndFreeCamera() {

        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    private void binarize(byte[] data)
    {
        for (int i = 0; i<mFrameSize; i++) {
            if (mThreshold > 128) {
                data[i] = (byte) 255;
            } else {
                data[i] = (byte) 0;
            }
        }
        // implementacja algorytmu binaryzacji
    }

    private void updateSurface(byte[] data)
    {
        SurfaceHolder holder = mSurface.getHolder();
        Canvas canvas = holder.lockCanvas();
        if (canvas!=null) {
            Rect srcRect = new Rect(0, 0, mCameraSize.width, mCameraSize.height);
            Rect destRect = new Rect(0, 0, mSurface.getWidth(), mSurface.getHeight());
            YuvImage yuv_image = new YuvImage(data, ImageFormat.NV21, mCameraSize.width, mCameraSize.height, null);
            ByteArrayOutputStream output_stream = new ByteArrayOutputStream();
            yuv_image.compressToJpeg(srcRect, 100, output_stream);
            Bitmap sourceBitmap = BitmapFactory.decodeByteArray(output_stream.toByteArray(), 0, output_stream.size());
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(sourceBitmap , 0, 0, mCameraSize.width, mCameraSize.height, matrix, true);
            Paint paint = new Paint();
            canvas.drawBitmap(rotatedBitmap, null, destRect, paint);
            paint.setColor(Color.GREEN);
            paint.setTextSize(30);
            canvas.drawText(String.format("Time: %d", System.nanoTime()-mStartCapture), 10, 40, paint);
            holder.unlockCanvasAndPost(canvas);
            mSurface.invalidate();
        }
    }

    private class ProcessPreview implements Camera.PreviewCallback {

        private volatile boolean isReady = true;

        @Override
        public void onPreviewFrame(final byte[] data, Camera camera) {

            if ((camera==null) || (data==null))
            {
                return;
            }
            mStartCapture = System.nanoTime();
            if (isReady) {
                isReady = false;
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binarize(data);
                        updateSurface(data);
                        isReady = true;
                    }
                });
            }

            camera.addCallbackBuffer(data);
        }
    }
}
