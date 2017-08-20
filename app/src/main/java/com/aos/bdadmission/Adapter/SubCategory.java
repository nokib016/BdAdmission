package com.aos.bdadmission.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aos.bdadmission.Activity.Varsity_Profile;
import com.aos.bdadmission.Model.VersityInfo;
import bdadmission.R;

import java.util.ArrayList;

public class SubCategory extends RecyclerView.Adapter<SubCategory.MyViewHolder> {
    private ArrayList<VersityInfo> categories;
    Context context;
    public SubCategory(ArrayList<VersityInfo> categories, Context context) {
        this.categories=categories;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_single_item_view,parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.categoryName.setText(categories.get(position).name);
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Varsity_Profile)context).loadVersity(categories.get(position).url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView categoryName;
        //public Button categoryName;
        public LinearLayout row;
        public MyViewHolder(View itemView) {
            super(itemView);
            categoryName= (TextView) itemView.findViewById(R.id.text_name);
            //categoryName= (Button) itemView.findViewById(R.id.text_name);
            row= (LinearLayout) itemView.findViewById(R.id.row);
        }
    }
}