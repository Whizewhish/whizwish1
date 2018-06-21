package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.alpha.whizwish.Models.FolowersModel;
import com.alpha.whizwish.R;
import com.squareup.picasso.Picasso;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class FollowersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<FolowersModel> modelList;

    private OnItemClickListener mItemClickListener;


    public FollowersAdapter(Context context, ArrayList<FolowersModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<FolowersModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_follower_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final FolowersModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.userName.setText(model.getUserName());
            genericViewHolder.city.setText(model.getCity());
            Picasso.with(mContext).load(model.getImage()).into(genericViewHolder.userImage);

            if (model.isFollowing()){
                genericViewHolder.follow.setBackground(mContext.getResources().getDrawable(R.drawable.btn_followed));
                genericViewHolder.follow.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                genericViewHolder.follow.setText("Following");
            } else {
                genericViewHolder.follow.setBackground(mContext.getResources().getDrawable(R.drawable.btn_follow));
                genericViewHolder.follow.setTextColor(mContext.getResources().getColor(R.color.text_borders_color));
                genericViewHolder.follow.setText("Follow");
            }



        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private FolowersModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, FolowersModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImage;
        private TextView userName;
        private TextView city;
        private Button follow;


        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.userImage = (ImageView) itemView.findViewById(R.id.userImage);
            this.userName = (TextView) itemView.findViewById(R.id.userName);
            this.city = (TextView) itemView.findViewById(R.id.city);
            this.follow = (Button) itemView.findViewById(R.id.follow);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

