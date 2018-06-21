package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.whizwish.Activities.StoreActivity;
import com.alpha.whizwish.Models.StoreModel;
import com.alpha.whizwish.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ravi on 16/11/17.
 */

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.MyViewHolder> {
    public static final String STORE_NAME = "store name";
    private Context context;
    private List<StoreModel> storeList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView store_name;
        public CircleImageView store_logo;

        public MyViewHolder(View view) {
            super(view);
            store_name = view.findViewById(R.id.store_name);
            store_logo = view.findViewById(R.id.store_logo);


        }
    }


    public StoreItemAdapter(Context context, List<StoreModel> storeList) {
        this.context = context;
        this.storeList = storeList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final StoreModel store = storeList.get(position);
        holder.store_name.setText(store.getStore_name());
        Picasso.with(context).load(store.getStore_image()).into(holder.store_logo);

        holder.store_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, StoreActivity.class);
                i.putExtra(STORE_NAME, store.getStore_name());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

}
