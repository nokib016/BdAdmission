package com.example.asus.bdadmission;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class ResultNotice extends AppCompatActivity {

    ListView resultListView;
    ArrayList<result_notice_item> resultArrayList;
    BaseAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_notice);
        initializeAll();
    }
    private void initializeAll() {
        resultListView = (ListView) findViewById(R.id.resultListId);
        resultArrayList = new ArrayList<result_notice_item>();
        resultArrayList.add(new result_notice_item("University Of Asia Pacific","Result is published for Pharmacy and CSE .Please Check the University profile"));
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



        resultAdapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.reslt_notice_render, null);
                }
                TextView NameTextView = (TextView) view.findViewById(R.id.rendernameId);
                TextView NoticeTextView = (TextView) view.findViewById(R.id.rendernoticeId);

                NameTextView.setText(resultArrayList.get(position).getUniName());
                NoticeTextView.setText(resultArrayList.get(position).getResultNotice());

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

    }
}
