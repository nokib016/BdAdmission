package com.aos.bdadmission.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.aos.bdadmission.Adapter.SubCategory;
import com.aos.bdadmission.Model.VersityInfo;
import bdadmission.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Varsity_Profile extends AppCompatActivity {
    WebView web;
    RecyclerView categoryRecycleView;
    ArrayList<VersityInfo> subCategoryArrayList;
    String versityName="";
    private SubCategory horizontalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.varsity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        versityName=getIntent().getStringExtra("versity_name");
        initializeAll();
    }

    private void initializeAll() {
        loadFirebaseData();
        web = (WebView) findViewById(R.id.webId);
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("WebView", consoleMessage.message());
                return true;
            }
        });
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setLoadsImagesAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        //web.getSettings().setSupportZoom(true);
        categoryRecycleView = (RecyclerView) findViewById(R.id.categoryRecycleView);
        subCategoryArrayList = new ArrayList<VersityInfo>();

        horizontalAdapter=new SubCategory(subCategoryArrayList,Varsity_Profile.this);

        LinearLayoutManager horizontalLayoutManagaer= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecycleView.setLayoutManager(horizontalLayoutManagaer);
        categoryRecycleView.setAdapter(horizontalAdapter);


    }

    public void loadVersity(String url){
        try {
            //web.loadUrl(URLEncoder.encode("https://admisionbd.firebaseapp.com/sub_view.html?versityname=জগন্নাথ বিশ্ববিদ্যালয়&key=-KrUWdCKmtGV82B8KPhp","UTF-8"));
            web.loadUrl("https://admisionbd.firebaseapp.com/sub_view.html?"+url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadFirebaseData() {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference db=firebaseDatabase.getReference("sub-category/"+versityName);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i:dataSnapshot.getChildren()
                        ) {
                    VersityInfo item=i.getValue(VersityInfo.class);
                    subCategoryArrayList.add(item);
                    System.out.println("value found..."+item.toString());
                }
                horizontalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent intent = new Intent(Varsity_Profile.this, MainActivity.class);
        startActivity(intent);*/
        finish();
    }
}
