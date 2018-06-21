package com.alpha.whizwish.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.whizwish.Activities.ProfileActivity;
import com.alpha.whizwish.Activities.StoreActivity;
import com.alpha.whizwish.Models.TimelineModel;
import com.alpha.whizwish.R;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyViewHolder> {

    private List<TimelineModel> mList;
    private Context mcontext;

    public TimelineAdapter(List<TimelineModel> list, Context context) {
        this.mList = list;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public TimelineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_item_list, parent, false);
        return new TimelineAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineAdapter.MyViewHolder holder, int position) {

        TimelineModel TimelineModel = mList.get(position);
        holder.user_name.setText(TimelineModel.getUserName());
        holder.post_time.setText(TimelineModel.getPostTime());
        holder.user_home.setText(TimelineModel.getUserHome());
        holder.post_title.setText(TimelineModel.getPostTitle());
        holder.post_content.setText(TimelineModel.getPostContent());
        holder.view_comments.setText("View all " + TimelineModel.getCommentCount());
        Glide.with(mcontext).load(TimelineModel.getPostImage()).into(holder.post_image);
        Glide.with(mcontext).load(TimelineModel.getUserImage()).into(holder.user_image);

        holder.user_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mcontext, ProfileActivity.class);
                i.putExtra(StoreActivity.TO_PROFILE, "from timeline");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mcontext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView user_name, post_time, post_title, post_content, user_home, view_comments;
        private CircleImageView user_image;
        private RoundedImageView post_image;
        private ImageView like, comment, share;

        public MyViewHolder(View itemView) {
            super(itemView);
            user_name = (TextView) itemView.findViewById(R.id.userName);
            post_time = (TextView) itemView.findViewById(R.id.postTime);
            post_title = (TextView) itemView.findViewById(R.id.postTitle);
            post_content = (TextView) itemView.findViewById(R.id.postContent);
            user_home = (TextView) itemView.findViewById(R.id.userHome);
            view_comments = (TextView) itemView.findViewById(R.id.viewComments);
            user_image = (CircleImageView) itemView.findViewById(R.id.userImage);
            post_image = (RoundedImageView) itemView.findViewById(R.id.postImage);
            like = (ImageView) itemView.findViewById(R.id.like);
            comment  = (ImageView) itemView.findViewById(R.id.comment);
            share = (ImageView) itemView.findViewById(R.id.share);



        }
    }
}
