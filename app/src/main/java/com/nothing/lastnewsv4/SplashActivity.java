package com.nothing.lastnewsv4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final AVLoadingIndicatorView avi = findViewById(R.id.avi);

        startAnim(avi);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    sleep(3000);

                    Intent intent = new Intent(SplashActivity.this, TappedActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    void startAnim(AVLoadingIndicatorView avi) {
        avi.show();
        // or avi.smoothToShow();
    }



}
