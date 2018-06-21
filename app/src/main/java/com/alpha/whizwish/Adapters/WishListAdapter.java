package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.whizwish.Models.WishListModel;
import com.alpha.whizwish.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<WishListModel> modelList;

    private WishListAdapter.OnItemClickListener mItemClickListener;


    public WishListAdapter(Context context, ArrayList<WishListModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<WishListModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_item, viewGroup, false);

        return new WishListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof WishListAdapter.ViewHolder) {
            final WishListModel model = getItem(position);
            WishListAdapter.ViewHolder genericViewHolder = (WishListAdapter.ViewHolder) holder;
            genericViewHolder.productCost.setText(model.getCost());
            genericViewHolder.productDesc.setText(model.getDesc());
            Picasso.with(mContext).load(model.getProductImage()).into(genericViewHolder.productImage);

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final WishListAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private WishListModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, WishListModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productCost, productDesc;


        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.productImage = (ImageView) itemView.findViewById(R.id.productImage);
            this.productCost = (TextView) itemView.findViewById(R.id.productCost);
            this.productDesc = (TextView) itemView.findViewById(R.id.productDesc);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}