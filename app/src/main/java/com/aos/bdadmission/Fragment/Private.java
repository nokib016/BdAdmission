package com.aos.bdadmission.Fragment;


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

import com.aos.bdadmission.Activity.Varsity_Profile;
import com.aos.bdadmission.BaseApplication.MyApplication;
import com.aos.bdadmission.Model.Item;
import com.aos.bdadmission.RealmModel.FavouriteVersity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import bdadmission.R;
import io.realm.Realm;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Private extends Fragment {


    ListView privateListView;
    ArrayList<Item> arrayList;
    BaseAdapter privateadapter;
    Context context;

    public Private() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_private, container, false);
        initializeAll(v);
        return v;

    }

    private void initializeAll(View v) {
        privateListView = (ListView) v.findViewById(R.id.privateListViewID);
        arrayList = new ArrayList<Item>();
        loadFirebaseData();
        privateadapter = new BaseAdapter() {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

                final ImageView  img1 = (ImageView) view.findViewById(R.id.favouriteID);
                final  ImageView img2 =(ImageView) view.findViewById(R.id.favouriteID2);

                if(MyApplication.favouriteList.contains(arrayList.get(position).name)){
                    img1.setVisibility(View.GONE);
                    img2.setVisibility(View.VISIBLE);
                }else{
                    img2.setVisibility(View.GONE);
                    img1.setVisibility(View.VISIBLE);
                }

                img1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        /*img1.setVisibility(View.GONE);
                        img2.setVisibility(View.VISIBLE);*/
                        Realm realm=Realm.getDefaultInstance();
                        FavouriteVersity favouriteVersity=new FavouriteVersity(arrayList.get(position).name,arrayList.get(position).updateTime,arrayList.get(position).logoLink);
                        realm.beginTransaction();
                        realm.insertOrUpdate(favouriteVersity);
                        realm.commitTransaction();
                        realm.close();

                        MyApplication.favouriteList.add(arrayList.get(position).name);
                        notifyDataSetChanged();
                    }
                });


                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*img2.setVisibility(View.GONE);
                        img1.setVisibility(View.VISIBLE);*/

                        Realm realm=Realm.getDefaultInstance();
                        FavouriteVersity favouriteVersity=realm.where(FavouriteVersity.class).equalTo("name",arrayList.get(position).name).findFirst();
                        realm.beginTransaction();
                        favouriteVersity.deleteFromRealm();
                        realm.commitTransaction();
                        realm.close();
                        MyApplication.favouriteList.remove(arrayList.get(position).name);
                        notifyDataSetChanged();
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

        privateListView.setAdapter(privateadapter);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}
