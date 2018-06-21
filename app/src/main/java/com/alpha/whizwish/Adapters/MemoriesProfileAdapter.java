package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.alpha.whizwish.Models.MemoriesProfileModel;
import com.alpha.whizwish.R;
import com.alpha.whizwish.Models.WhatsOnModel;
import com.squareup.picasso.Picasso;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class MemoriesProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<MemoriesProfileModel> modelList;

    private OnItemClickListener mItemClickListener;


    public MemoriesProfileAdapter(Context context, ArrayList<MemoriesProfileModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<MemoriesProfileModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.memories_item_grid, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final MemoriesProfileModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;



            Picasso.with(mContext).load(model.getPostImage()).into(genericViewHolder.postImage);


        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private MemoriesProfileModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, MemoriesProfileModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView postImage;




        public ViewHolder(final View itemView) {
            super(itemView);


            this.postImage = (ImageView) itemView.findViewById(R.id.postImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

