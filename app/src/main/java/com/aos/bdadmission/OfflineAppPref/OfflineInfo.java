package com.aos.bdadmission.OfflineAppPref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Arif on 8/4/2017.
 */

public class OfflineInfo {
    SharedPreferences sharedpreferences;
    Context context;
    public OfflineInfo(Context context){
        if(context==null){
            System.out.println("Context is null....");
        }
        this.context=context;
        sharedpreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
    }
    public void saveLastNotificationId(String lastid){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("lastid",lastid);
        editor.commit();
    }

    public String getLastNotificationId(){
        return sharedpreferences.getString("lastid","");
    }




    public void clearAll(){
        sharedpreferences.edit().clear().apply();
    }
}
