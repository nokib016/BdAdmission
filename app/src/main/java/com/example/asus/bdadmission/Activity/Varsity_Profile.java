package com.example.asus.bdadmission.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
    ListView subCategoryList;
    ArrayList<VersityInfo> subCategoryArrayList;
    BaseAdapter subCategoryAdapter;
    String versityName="";
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

        subCategoryList = (ListView) findViewById(R.id.categorylistId);
        subCategoryArrayList = new ArrayList<VersityInfo>();


        subCategoryAdapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(final int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.sub_category_render, null);
                }
                TextView subCategoryTextView = (TextView) view.findViewById(R.id.categoryId);
                LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.row);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadVersity(subCategoryArrayList.get(position).url);
                    }
                });

                subCategoryTextView.setText(subCategoryArrayList.get(position).name);


                return view;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Object getItem(int position) {

                return subCategoryArrayList.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return subCategoryArrayList.size();
            }
        };

        subCategoryList.setAdapter(subCategoryAdapter);

    }

    private void loadVersity(String url){
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
                subCategoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
