package com.example.asus.bdadmission;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Public extends Fragment {

    ListView wishListView;
    ArrayList<Item> arrayList;
    BaseAdapter adapter;
    Context context;

    public Public() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_public, container, false);
        initializeAll(v);
        return v;

    }

    private void initializeAll(View v) {
        wishListView = (ListView) v.findViewById(R.id.publicListViewID);
        arrayList = new ArrayList<Item>();
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));
        arrayList.add(new Item("Dhaka University",new Date(),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher),BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher)));


        adapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.wish_list_item, null);
                }
                TextView wishTextView = (TextView) view.findViewById(R.id.wishlistItemTextView);
                TextView dateTextView = (TextView) view.findViewById(R.id.wishlistDateTextView);
                ImageView logoImageView=(ImageView) view.findViewById(R.id.imageId);
                ImageView favouritestar = (ImageView) view.findViewById(R.id.favouriteID);

                wishTextView.setText(arrayList.get(position).getWishString());
                Date date = arrayList.get(position).getDate();
                dateTextView.setText(DateFormat.format("dd/MM/yyyy HH:mm:ss a", date));

                return view;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Object getItem(int position) {

                return arrayList.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return arrayList.size();
            }
        };

        wishListView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}