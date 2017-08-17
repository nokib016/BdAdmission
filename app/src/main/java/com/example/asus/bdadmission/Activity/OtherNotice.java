package com.example.asus.bdadmission.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.bdadmission.Model.other_notice_item;
import com.example.asus.bdadmission.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OtherNotice extends AppCompatActivity {

    ListView otherListView;
    ArrayList<other_notice_item> otherArrayList;
    BaseAdapter otherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_notice);
        initializeAll();
        loadFirebaseData();
    }

    private void initializeAll() {
        otherListView = (ListView) findViewById(R.id.otherListId);
        otherArrayList = new ArrayList<other_notice_item>();
       /* otherArrayList.add(new other_notice_item("University Of Asia Pacific","Pharmacy Admission test - 17/10/2017 other"));
        otherArrayList.add(new other_notice_item("American International University ","Pharmacy Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item("BRAC","Pharmacy Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item("Dhaka University","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item("University Of Asia Pacific","Pharmacy Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item("United University","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item("University Of Ulsan","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item("University Of Souteast","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item(" Green University Of Bangladesh","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        otherArrayList.add(new other_notice_item("NITA","Result is published for Pharmacy and CSE .Please Check the University profile"));
        otherArrayList.add(new other_notice_item("BUTEX","Result is published for Pharmacy and CSE .Please Check the University profile"));
        otherArrayList.add(new other_notice_item("Rajhshahi University","Result is published for Pharmacy and CSE .Please Check the University profile"));

*/

        otherAdapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.date_notice_render, null);
                }
                TextView otherNameTextView = (TextView) view.findViewById(R.id.daterendernameId);
                TextView otherNoticeTextView = (TextView) view.findViewById(R.id.datenoticeId);

                otherNameTextView.setText(otherArrayList.get(position).getVersity());
                otherNoticeTextView.setText(otherArrayList.get(position).getNotice());

                return view;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Object getItem(int position) {

                return otherArrayList.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return otherArrayList.size();
            }
        };

        otherListView.setAdapter(otherAdapter);

    }
    private void loadFirebaseData() {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference db=firebaseDatabase.getReference("notice/all-type/other_notice");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i:dataSnapshot.getChildren()
                        ) {
                    other_notice_item item=i.getValue(other_notice_item.class);
                    otherArrayList.add(item);
                    System.out.println("value found..."+item.toString());
                }
                otherAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
