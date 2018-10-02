package com.fongwama.edupalu_v3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timeOut();
    }

    private void timeOut(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainActivity();
            }
        }, SPLASH_TIME_OUT);
    }
    private void goToMainActivity(){
        Intent intent = new Intent(SplashActivity.this, SliderActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.translation_activity_in, R.anim.translation_activity_out);
        finish();
    }


}
