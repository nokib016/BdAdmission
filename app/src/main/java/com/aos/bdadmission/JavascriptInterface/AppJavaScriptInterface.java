package com.aos.bdadmission.JavascriptInterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.aos.bdadmission.BaseApplication.MyApplication;
import com.aos.bdadmission.Model.VersityDetailsData;
import com.aos.bdadmission.Model.VersityInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Arif on 8/20/2017.
 */

public class AppJavaScriptInterface {
    WebView webView;
    FirebaseDatabase firebaseDatabase;
    public AppJavaScriptInterface(WebView webView) {
        this.webView=webView;
        firebaseDatabase=FirebaseDatabase.getInstance();
    }

    @JavascriptInterface
    public void showToast(){
        Toast.makeText(MyApplication.BASE_CONTEXT, "Hello Javascript user", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String loadData(String versityName,String key){
        final DatabaseReference databaseReference=firebaseDatabase.getReference("sub-category-details/"+versityName.replaceAll("_"," ")+"/"+key);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                boolean isFound = false;
                for (DataSnapshot i:dataSnapshot.getChildren()
                        ) {
                    //Toast.makeText(MyApplication.BASE_CONTEXT,i.getKey() +"  "+i.getValue(VersityDetailsData.class)i.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println(i.getValue());
                    webView.loadUrl("javascript:returnData('"+i.getValue()+"')");
                    isFound=true;
                }
                if(!isFound){
                    webView.loadUrl("javascript:returnData('')");
                }
                //System.out.println(dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return "";
    }

}
