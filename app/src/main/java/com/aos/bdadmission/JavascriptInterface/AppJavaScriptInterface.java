package com.aos.bdadmission.JavascriptInterface;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.aos.bdadmission.Activity.MainActivity;
import com.aos.bdadmission.Activity.SpalshActivity;
import com.aos.bdadmission.BaseApplication.MyApplication;
import com.aos.bdadmission.Model.VersityDetailsData;
import com.aos.bdadmission.Model.VersityInfo;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Semaphore;

/**
 * Created by Arif on 8/20/2017.
 */

public class AppJavaScriptInterface {
    WebView webView;
    FirebaseDatabase firebaseDatabase;
    public String currentData="";
    public AppJavaScriptInterface(WebView webView) {
        this.webView=webView;
        firebaseDatabase=FirebaseDatabase.getInstance();
    }

    @JavascriptInterface
    public void showToast(){
        Toast.makeText(MyApplication.BASE_CONTEXT, "Hello Javascript user", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String loadData(String versityName, String key){

        currentData="";
        final Semaphore semaphore = new Semaphore(0);
        FirebaseDatabase.getInstance().goOnline();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("sub-category-details/"+versityName.replaceAll("_"," ")+"/"+key);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                boolean isFound = false;
                for (DataSnapshot i:dataSnapshot.getChildren()
                        ) {
                    isFound=true;
                    currentData=i.getValue().toString();
                    //tcs.setResult(currentData);
                }
                FirebaseDatabase.getInstance().goOffline();
                if(!isFound)
                    currentData="";
                semaphore.release();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currentData;
    }

}
