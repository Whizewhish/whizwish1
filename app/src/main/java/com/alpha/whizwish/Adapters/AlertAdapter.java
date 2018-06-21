package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.whizwish.Models.AlertModel;
import com.alpha.whizwish.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.MyViewHolder> {

    private List<AlertModel> mList;
    private Context mcontext;

    public AlertAdapter(List<AlertModel> list, Context context) {
        this.mList = list;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        AlertModel alertModel = mList.get(position);
        Picasso.with(mcontext).load(alertModel.getImageId()).into(holder.imageView);
        holder.textView.setText(alertModel.getAlertContent());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.alertImage);
            textView = (TextView) itemView.findViewById(R.id.alertContent);
        }
    }
}
