package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alpha.whizwish.Models.GiftsModel;
import com.alpha.whizwish.R;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GiftsAdapter extends RecyclerView.Adapter<GiftsAdapter.MyViewHolder> {

    private List<GiftsModel> mList;
    private Context mcontext;

    public GiftsAdapter(List<GiftsModel> list, Context context) {
        this.mList = list;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public GiftsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_item_list, parent, false);
        return new GiftsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftsAdapter.MyViewHolder holder, int position) {

        GiftsModel GiftsModel = mList.get(position);
        holder.user_name.setText(GiftsModel.getUserName());
        if (!GiftsModel.isRecieved()){
            holder.gift_lay.setVisibility(View.VISIBLE);
            holder.item_lay.setBackgroundColor(mcontext.getResources().getColor(R.color.grey));
            Glide.with(mcontext).load(GiftsModel.getGiftImage()).into(holder.gift_image);
            holder.gift_text.setText(mcontext.getResources().getString(R.string.unrecieved_gift_text));

        } else {
            holder.item_lay.setBackgroundColor(mcontext.getResources().getColor(R.color.white));
            holder.gift_lay.setVisibility(View.GONE);
            holder.gift_text.setText(mcontext.getResources().getString(R.string.recieved_gift_text ) + GiftsModel.getRecievedDays() + " days ago");
        }



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView user_name, gift_text;
        private CircleImageView user_image;
        private ImageView gift_image;
        private Button buy, cancel;
        private LinearLayout gift_lay, item_lay;


        public MyViewHolder(View itemView) {
            super(itemView);

            user_name = (TextView) itemView.findViewById(R.id.userName);
            gift_text = (TextView) itemView.findViewById(R.id.giftText);
            user_image = (CircleImageView) itemView.findViewById(R.id.userImage);
            gift_image = (ImageView) itemView.findViewById(R.id.giftImage);
            buy = (Button) itemView.findViewById(R.id.buy);
            cancel = (Button) itemView.findViewById(R.id.cancel);
            gift_lay = (LinearLayout) itemView.findViewById(R.id.giftLayout);
            item_lay = (LinearLayout) itemView.findViewById(R.id.itemLay);




        }
    }
}
