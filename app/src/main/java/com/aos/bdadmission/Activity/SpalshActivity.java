package com.aos.bdadmission.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.aos.bdadmission.BaseApplication.MyApplication;
import com.aos.bdadmission.Interface.AdShow;

import bdadmission.R;

public class SpalshActivity extends AppCompatActivity implements AdShow{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        MyApplication.adContext=this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                finish();
            }
        }, 3000);
    }
    @Override
    public void showAd() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(MyApplication.mInterstitialAd.isLoaded()){
                    MyApplication.mInterstitialAd.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }

}
