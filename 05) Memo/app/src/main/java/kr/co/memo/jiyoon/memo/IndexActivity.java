package kr.co.memo.jiyoon.memo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goMainActivity();
            }
        },1500);
    }
    public void goMainActivity(){
        Intent intent =  new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
