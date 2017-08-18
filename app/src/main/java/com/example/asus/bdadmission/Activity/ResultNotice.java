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

import com.example.asus.bdadmission.Model.result_notice_item;
import com.example.asus.bdadmission.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResultNotice extends AppCompatActivity {

    ListView resultListView;
    ArrayList<result_notice_item> resultArrayList;
    BaseAdapter resultAdapter;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_notice);
        initializeAll();
        loadFirebaseData();
    }
    private void initializeAll() {
        resultListView = (ListView) findViewById(R.id.resultListId);
        back = (ImageView) findViewById(R.id.backbuttonId);
        resultArrayList = new ArrayList<result_notice_item>();
      /*  resultArrayList.add(new result_notice_item("University Of Asia Pacific","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("American International University ","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("BRAC","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("Dhaka University","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("University Of Asia Pacific","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("United University","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("University Of Ulsan","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("University Of Souteast","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item(" Green University Of Bangladesh","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("NITA","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("BUTEX","Result is published for Pharmacy and CSE .Please Check the University profile"));
        resultArrayList.add(new result_notice_item("Rajhshahi University","Result is published for Pharmacy and CSE .Please Check the University profile"));

*/

        resultAdapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.reslt_notice_render, null);
                }
                TextView NameTextView = (TextView) view.findViewById(R.id.rendernameId);
                TextView NoticeTextView = (TextView) view.findViewById(R.id.rendernoticeId);

                NameTextView.setText(resultArrayList.get(position).getVersity());
                NoticeTextView.setText(resultArrayList.get(position).getNotice());

                return view;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Object getItem(int position) {

                return resultArrayList.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return resultArrayList.size();
            }
        };

        resultListView.setAdapter(resultAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void loadFirebaseData() {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference db=firebaseDatabase.getReference("notice/all-type/result_notice");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i:dataSnapshot.getChildren()
                        ) {
                    result_notice_item item=i.getValue(result_notice_item.class);
                    resultArrayList.add(item);
                    System.out.println("value found..."+item.toString());
                }
                resultAdapter.notifyDataSetChanged();
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
