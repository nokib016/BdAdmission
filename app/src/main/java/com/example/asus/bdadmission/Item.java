package com.example.asus.bdadmission;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Date;

public class Item {
	private String wishString;
	private Date date;
    private Bitmap image;

    private  Bitmap favourite;

    public Item(String wishString, Date date, Bitmap image,Bitmap favourite) {
        this.wishString = wishString;
        this.date = date;
        this.image = image;
        this.favourite = favourite;
    }

    public String getWishString() {
        return wishString;
    }

    public void setWishString(String wishString) {
        this.wishString = wishString;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getFavourite() {
        return favourite;
    }

    public void setFavourite(Bitmap favourite) {
        this.favourite = favourite;
    }
}
