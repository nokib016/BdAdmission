package com.example.asus.bdadmission;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Varsity_Profile extends AppCompatActivity {
    WebView web;
    ListView subCategoryList;
    ArrayList<Sub_category_item> subCategoryArrayList;
    BaseAdapter subCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.varsity_profile);
        initializeAll();
    }

    private void initializeAll() {

        web = (WebView) findViewById(R.id.webId);
        try {
            web.loadUrl(URLEncoder.encode("https://admisionbd.firebaseapp.com//sub_view.html?versityname=জগন্নাথ বিশ্ববিদ্যালয়&key=-KrUWdCKmtGV82B8KPhp","UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        subCategoryList = (ListView) findViewById(R.id.categorylistId);
        subCategoryArrayList = new ArrayList<Sub_category_item>();
        subCategoryArrayList.add(new Sub_category_item("Unit"));    subCategoryArrayList.add(new Sub_category_item("Subject"));
        subCategoryArrayList.add(new Sub_category_item("Exam Result"));
        subCategoryArrayList.add(new Sub_category_item("Exam Date"));
        subCategoryArrayList.add(new Sub_category_item("Seat Plan"));



        subCategoryAdapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.sub_category_render, null);
                }
                TextView subCategoryTextView = (TextView) view.findViewById(R.id.categoryId);

                subCategoryTextView.setText(subCategoryArrayList.get(position).getCategory());


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
}
