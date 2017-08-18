package com.example.asus.bdadmission.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.bdadmission.Adapter.SubCategory;
import com.example.asus.bdadmission.Model.Item;
import com.example.asus.bdadmission.Model.Sub_category_item;
import com.example.asus.bdadmission.Model.VersityInfo;
import com.example.asus.bdadmission.R;
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
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.varsity_profile);
        versityName=getIntent().getStringExtra("versity_name");
        initializeAll();
    }

    private void initializeAll() {
        loadFirebaseData();
        web = (WebView) findViewById(R.id.webId);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        back= (ImageView) findViewById(R.id.backbuttonId);

        categoryRecycleView = (RecyclerView) findViewById(R.id.categoryRecycleView);
        subCategoryArrayList = new ArrayList<VersityInfo>();

        horizontalAdapter=new SubCategory(subCategoryArrayList,Varsity_Profile.this);

        LinearLayoutManager horizontalLayoutManagaer= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecycleView.setLayoutManager(horizontalLayoutManagaer);
        categoryRecycleView.setAdapter(horizontalAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });


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
