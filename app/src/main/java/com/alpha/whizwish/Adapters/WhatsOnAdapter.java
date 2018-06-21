package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.alpha.whizwish.R;
import com.alpha.whizwish.Models.WhatsOnModel;
import com.squareup.picasso.Picasso;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class WhatsOnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<WhatsOnModel> modelList;

    private OnItemClickListener mItemClickListener;


    public WhatsOnAdapter(Context context, ArrayList<WhatsOnModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<WhatsOnModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_whatson_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final WhatsOnModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.userName.setText(model.getUserName());
            Picasso.with(mContext).load(model.getUserImage()).into(genericViewHolder.userImage);
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

    private WhatsOnModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, WhatsOnModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImage, postImage;
        private TextView userName;


        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.userImage = (ImageView) itemView.findViewById(R.id.userImage);
            this.postImage = (ImageView) itemView.findViewById(R.id.postImage);
            this.userName = (TextView) itemView.findViewById(R.id.userName);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

