package com.example.asus.bdadmission;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DateNotice extends AppCompatActivity {

    ListView dateListView;
    ArrayList<date_notice_item> dateArrayList;
    BaseAdapter dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_notice);
        initializeAll();
    }

    private void initializeAll() {
        dateListView = (ListView) findViewById(R.id.dateListId);
        dateArrayList = new ArrayList<date_notice_item>();
        dateArrayList.add(new date_notice_item("University Of Asia Pacific","Pharmacy Admission test - 17/10/2017"));
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



        dateAdapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.date_notice_render, null);
                }
                TextView NameTextView = (TextView) view.findViewById(R.id.daterendernameId);
                TextView NoticeTextView = (TextView) view.findViewById(R.id.datenoticeId);

                NameTextView.setText(dateArrayList.get(position).getUniNameDate());
                NoticeTextView.setText(dateArrayList.get(position).getDstetNotice());

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

       dateListView.setAdapter(dateAdapter);

    }
}
