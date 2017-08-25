package com.aos.bdadmission.BackgroudTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Timer;
import java.util.TimerTask;

public class AdService extends Service {
    public AdService() {
    }

    Timer timer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public InterstitialAd mInterstitialAd;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


       /* mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9346872776165350/7987195413");
        mInterstitialAd.loadAd(
                new AdRequest.
                        Builder().
                        build()

        );

        timer=new Timer();


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Try to load new Add ");
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                }else {
                    mInterstitialAd.loadAd(
                            new AdRequest.
                                    Builder().
                                    build()

                    );
                }
            }
        }, 0, 10000);*/

        return START_STICKY;
    }
}
