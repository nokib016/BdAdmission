package com.aos.bdadmission.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.aos.bdadmission.Model.all_notice_item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import bdadmission.R;

public class All_Notice extends AppCompatActivity {


    ListView allnoticeListView;
    ArrayList<all_notice_item> dateArrayList;
    BaseAdapter dateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__notice);

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

        initializeAll();
        loadFirebaseData();
    }

    private void initializeAll() {
        allnoticeListView = (ListView) findViewById(R.id.allnoticetListId);
        dateArrayList = new ArrayList<all_notice_item>();



        dateAdapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.date_notice_render, null);
                }
                TextView NameTextView = (TextView) view.findViewById(R.id.daterendernameId);
                TextView NoticeTextView = (TextView) view.findViewById(R.id.datenoticeId);

                NameTextView.setText(dateArrayList.get(position).getVersity());
                NoticeTextView.setText(dateArrayList.get(position).getNotice());

                return view;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Object getItem(int position) {

                return dateArrayList.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return dateArrayList.size();
            }
        };

        allnoticeListView.setAdapter(dateAdapter);
    }

    private void loadFirebaseData() {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference db=firebaseDatabase.getReference("notice/all");
        db.keepSynced(true);
        db.limitToLast(30).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i:dataSnapshot.getChildren()
                        ) {
                    all_notice_item item=i.getValue(all_notice_item.class);
                    dateArrayList.add(item);
                    System.out.println("value found..."+item.toString());
                }
                dateAdapter.notifyDataSetChanged();
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
