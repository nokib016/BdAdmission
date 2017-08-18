package com.example.asus.bdadmission.BackgroudTask;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.asus.bdadmission.Activity.MainActivity;
import com.example.asus.bdadmission.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arif on 8/18/2017.
 */

public class AppBackground extends IntentService {


    public AppBackground() {
        super(AppBackground.class.getName());
    }
    Timer timer;
    int counter=0;
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("Service start..");
        timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                createNotification();
                counter++;
                if(counter>=5){
                    this.cancel();
                }
            }
        }, 0, /*10*1000*60*/1000);
    }
    public void createNotification(){

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_item);
        contentView.setImageViewResource(R.id.imageId,R.mipmap.ic_notification_icon_logo);
        contentView.setTextViewText(R.id.wishlistItemTextView,"");
        contentView.setTextViewText(R.id.wishlistDateTextView,"");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_notification_icon_logo)
                        .setContent(contentView);

        NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        mNotificationManager.notify((int)System.currentTimeMillis(), mBuilder.build());
    }
}
