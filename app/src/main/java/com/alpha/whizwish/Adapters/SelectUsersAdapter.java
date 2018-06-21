package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alpha.whizwish.Models.SelectUsersModel;
import com.alpha.whizwish.R;
import com.squareup.picasso.Picasso;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.List;

public class SelectUsersAdapter extends RecyclerView.Adapter<SelectUsersAdapter.MyViewHolder> {

    private List<SelectUsersModel> mList;
    private Context mcontext;

    public SelectUsersAdapter(List<SelectUsersModel> list, Context context) {
        this.mList = list;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        SelectUsersModel SelectUsersModel = mList.get(position);
        Picasso.with(mcontext).load(SelectUsersModel.getImage()).into(holder.imageView);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.checkBox.isChecked()){
                    holder.checkBox.setChecked(true);
                } else {
                    holder.checkBox.setChecked(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private CustomCheckBox checkBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.userImage);
            checkBox = (CustomCheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
