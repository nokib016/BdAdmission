package com.aos.bdadmission.BaseApplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.aos.bdadmission.BackgroudTask.AdService;
import com.aos.bdadmission.Interface.AdShow;
import com.aos.bdadmission.RealmModel.FavouriteVersity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MyApplication extends Application implements AdShow{

    public static HashSet<String> favouriteList=new HashSet<String>();
    public static Context BASE_CONTEXT;
    public static Context adContext;
    Intent intent;

    public static InterstitialAd mInterstitialAd;

    @Override
    public void onCreate() {
        super.onCreate();


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9346872776165350/7987195413");
        mInterstitialAd.loadAd(
                new AdRequest.
                        Builder().
                        build()

        );

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                mInterstitialAd.loadAd(
                        new AdRequest.
                                Builder().
                                build()

                );
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });

        BASE_CONTEXT=this;
        Realm.init(this);
    /*RealmConfiguration config = new RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build();
    Realm.getInstance(config);*/
        //System.out.println("Application start");
        loadAllFavouriteVersity();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().getReference().keepSynced(true);
        intent=new Intent(this, AdService.class);
        startService(intent);

    }



    public void loadAllFavouriteVersity(){
        //System.out.println("base all favourite check");
        Realm realm=Realm.getDefaultInstance();
        RealmResults<FavouriteVersity> allFourite=realm.where(FavouriteVersity.class).findAll();
        for (FavouriteVersity favouriteVersity:allFourite
                ) {
            //System.out.println("base all favourite"+favouriteVersity.name);
            favouriteList.add(favouriteVersity.name);
        }
    }

    @Override
    public void showAd() {
        System.out.println("Ad show in base context..");
    }


    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;

}