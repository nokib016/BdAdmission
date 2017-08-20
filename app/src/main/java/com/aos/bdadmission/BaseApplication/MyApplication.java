package com.aos.bdadmission.BaseApplication;

import android.app.Application;

import com.aos.bdadmission.RealmModel.FavouriteVersity;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MyApplication extends Application {

  public static HashSet<String> favouriteList=new HashSet<String>();

  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    /*RealmConfiguration config = new RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build();
    Realm.getInstance(config);*/
    //System.out.println("Application start");
    loadAllFavouriteVersity();
    FirebaseDatabase.getInstance().setPersistenceEnabled(true);


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
}