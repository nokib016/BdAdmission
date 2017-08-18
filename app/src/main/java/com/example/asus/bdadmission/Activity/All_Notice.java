package com.example.asus.bdadmission.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.bdadmission.Model.all_notice_item;
import com.example.asus.bdadmission.Model.date_notice_item;
import com.example.asus.bdadmission.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class All_Notice extends AppCompatActivity {


    ListView allnoticeListView;
    ArrayList<all_notice_item> dateArrayList;
    BaseAdapter dateAdapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__notice);
        initializeAll();
        loadFirebaseData();
    }

    private void initializeAll() {
        allnoticeListView = (ListView) findViewById(R.id.allnoticetListId);
        back = (ImageView) findViewById(R.id.backbuttonId);
        dateArrayList = new ArrayList<all_notice_item>();
       /* dateArrayList.add(new date_notice_item("University Of Asia Pacific","Pharmacy Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("American International University ","Pharmacy Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("BRAC","Pharmacy Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("Dhaka University","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("University Of Asia Pacific","Pharmacy Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("United University","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("University Of Ulsan","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("University Of Souteast","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item(" Green University Of Bangladesh","Pharmacy , EEE , Civil and CSE Admission test - 17/10/2017"));
        dateArrayList.add(new date_notice_item("NITA","Result is published for Pharmacy and CSE .Please Check the University profile"));
        dateArrayList.add(new date_notice_item("BUTEX","Result is published for Pharmacy and CSE .Please Check the University profile"));
        dateArrayList.add(new date_notice_item("Rajhshahi University","Result is published for Pharmacy and CSE .Please Check the University profile"));
*/


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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadFirebaseData() {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference db=firebaseDatabase.getReference("notice/all-type/date_announcement_notice");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
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
