package com.sming;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_CODE=1000;
    // 종료와 시작을 정의하기 위해서 전역으로 정의해주었습니다.
    /**
     * MediaProjection을 사용하기 위한 객체입니다.
     */
    private MediaProjection mediaProjection;

    /**
     * 실제 화면의 크기를 정하고, Surface를 통해 화면을 그리게 됩니다
     */
    private VirtualDisplay virtualDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_melon = (Button) findViewById(R.id.button_melon);
        button_melon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //타 어플(멜론)의 Activity 실행
                ComponentName cn = new ComponentName("com.iloen.melon", "com.iloen.melon.MusicBrowserActivity");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(cn);
                startActivity(intent);

                /* MediaProjection 활용*/
                // MediaProjectionManager 권한 획들을 위함
                MediaProjectionManager mpm = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
                startActivityForResult(mpm.createScreenCaptureIntent(), REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) { // 사용자가 권한을 허용해주었는지에 대한 처리
            // 사용자가 권한을 허용해주지 않았습니다.
            if (resultCode != RESULT_OK) {
                return;
            }
            // 사용자가 권한을 허용해주었기에 mediaProjection을 사용할 수 있는 권한이 생기게 됩니다.
//            mediaProjection = projectionManager.getMediaProjection(resultCode, data);
//            if (mediaProjection != null) {
//                // MediaProjection에 대한 Event 정보를 받으려면 아래와 같이 적용하시면 됩니다.
//                mediaProjection.registerCallback(new MediaProjectionCallback(), null);
//                virtualDisplay = mediaProjection.createVirtualDisplay("VirtualDisplay name", 화면 넓이, 화면 높이, 화면 density, flag, surface(화면을 출력할 곳), null /* Callbacks */, null /* Handler */);
//            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

