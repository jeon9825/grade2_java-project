package com.sming;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_melon = (Button) findViewById(R.id.button_melon);
        button_melon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //타 어플(멜론)의 Activity 실행
                ComponentName cn = new ComponentName("com.iloen.melon","com.iloen.melon.MusicBrowserActivity");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(cn);
                startActivity(intent);
            }
        });
    }
}
