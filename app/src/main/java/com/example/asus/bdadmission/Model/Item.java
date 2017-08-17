package com.example.asus.bdadmission.Model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Date;

public class Item {
	public String name;
	public long date;
    public String logoLink;

    public Item() {
    }

    public Item(String name, long date, String logoLink) {
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
