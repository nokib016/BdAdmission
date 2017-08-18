package com.example.asus.bdadmission.BackgroudTask;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Arif on 8/18/2017.
 */

public class AppBackground extends IntentService {


    public AppBackground() {
        super(AppBackground.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
