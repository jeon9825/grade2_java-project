package com.sming;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    // 종료와 시작을 정의하기 위해서 전역으로 정의해주었습니다.
    /**
     * MediaProjection을 사용하기 위한 객체입니다.
     */
    private MediaProjection mediaProjection;

    /**
     * 실제 화면의 크기를 정하고, Surface를 통해 화면을 그리게 됩니다
     */
    private VirtualDisplay virtualDisplay;

    private static final String TAG = MainActivity.class.getName();
    private static final int REQUEST_CODE = 100;
    private MediaProjectionManager mProjectionManager;
    private MediaProjection mProjection;
    private ImageReader mImageReader;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int imagesProduced;
    private long startTimeInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call for the projection manager
        mProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        final Button startButton = (Button) findViewById(R.id.StartButton);
        startButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startProjection();
            }
        });

        final Button stopButton = (Button) findViewById(R.id.StopButton);
        stopButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                startProjection();
            }
        });

        final Button melonButton = (Button) findViewById(R.id.MelonButton);
        melonButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //타 어플(멜론)의 Activity 실행
                ComponentName cn = new ComponentName("com.iloen.melon", "com.iloen.melon.MusicBrowserActivity");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(cn);
                startActivity(intent);
            }
        });

        // start capture handling thread
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler();
                Looper.loop();
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            // for statistics -- init
            imagesProduced = 0;
            startTimeInMillis = System.currentTimeMillis();

            mProjection = mProjectionManager.getMediaProjection(resultCode, data);

            if (mProjection != null) {
                final DisplayMetrics metrics = getResources().getDisplayMetrics();
                final int density = metrics.densityDpi;
                final int flags = DisplayManager.VIRTUAL_DISPLAY_FLAG_OWN_CONTENT_ONLY | DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC;
                final Display display = getWindowManager().getDefaultDisplay();
                final Point size = new Point();
                display.getSize(size);
                final int width = size.x;
                final int height = size.y;

                mImageReader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 2);
                mProjection.createVirtualDisplay("screen capture", width, height, density, flags, mImageReader.getSurface(), new VirtualDisplayCallback(), mHandler);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startProjection() {
        startActivityForResult(mProjectionManager.createScreenCaptureIntent(), REQUEST_CODE);
    }

    private class VirtualDisplayCallback extends VirtualDisplay.Callback {

        @Override
        public void onPaused() {
            super.onPaused();
            Log.e(TAG, "VirtualDisplayCallback: onPaused");
        }

        @Override
        public void onResumed() {
            super.onResumed();
            Log.e(TAG, "VirtualDisplayCallback: onResumed");
        }

        @Override
        public void onStopped() {
            super.onStopped();
            Log.e(TAG, "VirtualDisplayCallback: onStopped");
        }

    }
}
