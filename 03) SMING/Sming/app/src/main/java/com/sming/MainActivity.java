package com.sming;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

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
                stopProjection();
            }
        });

        final Button melonButton = (Button) findViewById(R.id.MelonButton);
        melonButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //타 어플(멜론)의 Activity 실행
                ComponentName cn;
                try {
                    cn = new ComponentName("com.iloen.melon", "com.iloen.melon.MusicBrowserActivity");
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(cn);
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) { //버튼을 누를때 해당 어플이 없으면 화면이 꺼지는 오류를 try-catch문으로!
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + "com.iloen.melon"));
                    startActivity(intent);
                }
            }
        });

        final Button genieButton = (Button) findViewById(R.id.GenieButton);
        genieButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //타 어플(지니)의 Activity 실행
                ComponentName cn;
                try {
                    cn = new ComponentName("com.ktmusic.geniemusic", "com.ktmusic.geniemusic.DummyActivity");
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(cn);
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) { //버튼을 누를때 해당 어플이 없으면 화면이 꺼지는 오류를 try-catch문으로!
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + "com.ktmusic.geniemusic"));
                    startActivity(intent);
                }
            }
        });

        final Button bugsButton = (Button) findViewById(R.id.BugsButton);
        bugsButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //타 어플(벅스)의 Activity 실행
                ComponentName cn;
                try {
                    cn = new ComponentName("com.neowiz.android.bugs", "com.neowiz.android.bugs.activity.HomeActivity");
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(cn);
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) { //버튼을 누를때 해당 어플이 없으면 화면이 꺼지는 오류를 try-catch문으로!
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + "com.neowiz.android.bugs"));
                    startActivity(intent);
                }
            }
        });

        final Button youtubeButton = (Button) findViewById(R.id.YoutubeButton);
        youtubeButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //타 어플(유튜브)의 Activity 실행
                ComponentName cn;
                try {
                    cn = new ComponentName("com.google.android.youtube", "com.google.android.youtube.app.honeycomb.Shell$HomeActivity");
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.setComponent(cn);
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) { //버튼을 누를때 해당 어플이 없으면 화면이 꺼지는 오류를 try-catch문으로!
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + "com.google.android.youtube"));
                    startActivity(intent);
                }
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
                final String STORE_DIRECTORY = Environment.getExternalStorageDirectory()+"/DCIM";
                final File file = new File(STORE_DIRECTORY);
                mImageReader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 2);
                mProjection.createVirtualDisplay("screen capture", width, height, density, flags, mImageReader.getSurface(), new VirtualDisplayCallback(), mHandler);
                mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader reader) {
                        Image image=null;
                        try {
                            image = reader.acquireLatestImage();
                            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                            byte[] bytes = new byte[buffer.capacity()];
                            buffer.get(bytes);
                            save(bytes);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (image != null) {
                                image.close();
                                reader.close();
                            }
                        }
                    }
                    private void save(byte[] bytes) throws IOException {
                        OutputStream output = null;
                        try {
                            output = new FileOutputStream(file);
                            output.write(bytes);
                        } finally {
                            if (null != output) {
                                output.close();
                            }
                        }
                    }
                }, mHandler);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startProjection() {
        startActivityForResult(mProjectionManager.createScreenCaptureIntent(), REQUEST_CODE);
    }

    private void stopProjection() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mProjection.stop();
            }
        });
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
