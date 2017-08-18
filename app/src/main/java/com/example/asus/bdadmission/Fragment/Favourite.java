package com.example.asus.bdadmission.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.asus.bdadmission.Activity.Varsity_Profile;
import com.example.asus.bdadmission.Model.Item;
import com.example.asus.bdadmission.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favourite extends Fragment {
    ListView favouriteListView;
    ArrayList<Item> arrayList;
    BaseAdapter privateadapter;
    Context context;

    public Favourite() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_favourite, container, false);
        initializeAll(v);
        return v;
    }

    private void initializeAll(View v) {
        favouriteListView = (ListView) v.findViewById(R.id.favouriteListViewID);
        arrayList = new ArrayList<Item>();
        loadFirebaseData();
        privateadapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public View getView(final int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = inflater.inflate(R.layout.wish_list_item, null);
                }
                TextView wishTextView = (TextView) view.findViewById(R.id.wishlistItemTextView);
                TextView dateTextView = (TextView) view.findViewById(R.id.wishlistDateTextView);
                ImageView logoImageView=(ImageView) view.findViewById(R.id.imageId);

                Glide
                        .with(getContext())
                        .load(arrayList.get(position).logoLink)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .fitCenter()
                        .into(logoImageView);

                ImageView favouritestar = (ImageView) view.findViewById(R.id.favouriteID);
                LinearLayout row= (LinearLayout) view.findViewById(R.id.row);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getContext(), Varsity_Profile.class);
                        intent.putExtra("versity_name",arrayList.get(position).name);
                        startActivity(intent);
                    }
                });

                wishTextView.setText(arrayList.get(position).name);

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

        favouriteListView.setAdapter(privateadapter);
    }


    private void loadFirebaseData() {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference db=firebaseDatabase.getReference("versity");
        db.orderByChild("isPrivate").equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot i:dataSnapshot.getChildren()
                        ) {
                    Item item=i.getValue(Item.class);
                    arrayList.add(item);
                    System.out.println("value found..."+item.toString());
                }
                privateadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
