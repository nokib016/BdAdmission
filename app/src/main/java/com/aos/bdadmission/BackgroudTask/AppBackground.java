package com.aos.bdadmission.BackgroudTask;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.aos.bdadmission.Activity.MainActivity;
import com.aos.bdadmission.Model.other_notice_item;
import com.aos.bdadmission.OfflineAppPref.OfflineInfo;
import bdadmission.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    OfflineInfo offlineInfo;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("Service start..");
        final DatabaseReference dbAllNotification=firebaseDatabase.getReference("notice/all");
        timer=new Timer();
        offlineInfo=new OfflineInfo(this);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(!offlineInfo.getLastNotificationId().equalsIgnoreCase("")){
                    dbAllNotification.orderByKey().startAt(offlineInfo.getLastNotificationId()).limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                           boolean isFirst=true;
                            for (DataSnapshot i:dataSnapshot.getChildren()
                                    ) {
                                 if(isFirst)
                                 {
                                     isFirst=false;
                                     continue;
                                 }
                                other_notice_item item=i.getValue(other_notice_item.class);
                                createNotification(item.getVersity(),item.getNotice());
                                offlineInfo.saveLastNotificationId(i.getKey());
                                System.out.println(item.toString());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    dbAllNotification.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot i:dataSnapshot.getChildren()
                                    ) {

                                other_notice_item item=i.getValue(other_notice_item.class);
                                createNotification(item.getVersity(),item.getNotice());
                                offlineInfo.saveLastNotificationId(i.getKey());
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        }, 0, /*10*1000*60*/1000);
    }
    public void createNotification(String versityName,String notice){



        Intent notificationIntent = new Intent(this, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);



        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_item);
        contentView.setImageViewResource(R.id.imageId,R.mipmap.ic_notification_icon_logo);
        contentView.setTextViewText(R.id.wishlistItemTextView,versityName);
        contentView.setTextViewText(R.id.wishlistDateTextView,notice);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_notification_icon_logo)
                        .setContentIntent(intent)
                        .setContent(contentView);


        NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify((int)System.currentTimeMillis(), mBuilder.build());
    }
}
