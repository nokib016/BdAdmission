package com.aos.bdadmission.RealmModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Arif on 8/18/2017.
 */

public class FavouriteVersity extends RealmObject{
    @PrimaryKey
    public String name;
    public long date;
    public String logoLink;

    public FavouriteVersity() {
    }

    public FavouriteVersity(String name, long date, String logoLink) {
        this.name = name;
        this.date = date;
        this.logoLink = logoLink;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", logo='" + logoLink + '\'' +
                '}';
    }
}
