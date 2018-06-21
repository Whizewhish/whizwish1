package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.whizwish.Models.WishBoardModel;
import com.alpha.whizwish.Models.WishBoardModel;
import com.alpha.whizwish.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class WishBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<WishBoardModel> modelList;

    private WishBoardAdapter.OnItemClickListener mItemClickListener;


    public WishBoardAdapter(Context context, ArrayList<WishBoardModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<WishBoardModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public WishBoardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishboard_item, viewGroup, false);

        return new WishBoardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof WishBoardAdapter.ViewHolder) {
            final WishBoardModel model = getItem(position);
            WishBoardAdapter.ViewHolder genericViewHolder = (WishBoardAdapter.ViewHolder) holder;
            genericViewHolder.productCost.setText(model.getCost());
            genericViewHolder.productDesc.setText(model.getDesc());
            Picasso.with(mContext).load(model.getProductImage()).into(genericViewHolder.productImage);
            Picasso.with(mContext).load(model.getUserIamge()).into(genericViewHolder.userImage);

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final WishBoardAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private WishBoardModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, WishBoardModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productCost, productDesc;
        private CircleImageView userImage;


        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.productImage = (ImageView) itemView.findViewById(R.id.productImage);
            this.productCost = (TextView) itemView.findViewById(R.id.productCost);
            this.productDesc = (TextView) itemView.findViewById(R.id.productDesc);
            this.userImage = (CircleImageView) itemView.findViewById(R.id.userImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}