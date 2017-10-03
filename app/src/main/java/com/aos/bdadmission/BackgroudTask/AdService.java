package com.aos.bdadmission.BackgroudTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.aos.bdadmission.BaseApplication.MyApplication;
import com.aos.bdadmission.Interface.AdShow;
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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {





        timer=new Timer();


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Try to load new Add ");
                if(MyApplication.adContext!=null && MyApplication.isActivityVisible()){
                    ((AdShow)(MyApplication.adContext)).showAd();
                }else {
                    System.out.println("Ad found null in service class");
                }
            }
        }, 0, (long)(1.5*60*1000));

        return START_STICKY;
    }
}
