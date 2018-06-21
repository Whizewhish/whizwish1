package com.alpha.whizwish.Activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import android.support.v4.widget.SwipeRefreshLayout;

import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.alpha.whizwish.Adapters.FollowersAdapter;
import com.alpha.whizwish.Models.FolowersModel;
import com.alpha.whizwish.R;


public class FollowersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView toolbarTitle;
    private View shadow;
    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private FollowersAdapter mAdapter;

    private ArrayList<FolowersModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        shadow = (View) findViewById(R.id.shadow);
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            shadow.setVisibility(View.GONE);
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {

            shadow.setVisibility(View.VISIBLE);
            decor.setSystemUiVisibility(0);
        }
        findViews();
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Followers");
        setAdapter();

        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Do your stuff on refresh
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshRecyclerList.isRefreshing())
                            swipeRefreshRecyclerList.setRefreshing(false);
                    }
                }, 5000);

            }
        });

    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
    }




    private void setAdapter() {


        modelList.add(new FolowersModel(R.drawable.profile1, "Android", "California", true));
        modelList.add(new FolowersModel(R.drawable.profile2,"Beta", "Hello Beta",false));
        modelList.add(new FolowersModel(R.drawable.profile3,"Cupcake", "Hello Cupcake", false));
        modelList.add(new FolowersModel(R.drawable.profile4,"Donut", "Hello Donut", false));
        modelList.add(new FolowersModel(R.drawable.profile5,"Eclair", "Hello Eclair", true));
        modelList.add(new FolowersModel(R.drawable.profile6,"Froyo", "Hello Froyo", false));
        modelList.add(new FolowersModel(R.drawable.profile7,"Gingerbread", "Hello Gingerbread",true));
        modelList.add(new FolowersModel(R.drawable.profile8,"Honeycomb", "Hello Honeycomb",true));
        modelList.add(new FolowersModel(R.drawable.profile9,"Ice Cream Sandwich", "Hello Ice Cream Sandwich",true));
        modelList.add(new FolowersModel(R.drawable.profile10,"Jelly Bean", "Hello Jelly Bean",true));
        modelList.add(new FolowersModel(R.drawable.profile11,"KitKat", "Hello KitKat",true));
        modelList.add(new FolowersModel(R.drawable.profile11,"Lollipop", "Hello Lollipop",false));
        modelList.add(new FolowersModel(R.drawable.profile1,"Marshmallow", "Hello Marshmallow",false));
        modelList.add(new FolowersModel(R.drawable.profile6,"Nougat", "Hello Nougat",true));
        modelList.add(new FolowersModel(R.drawable.profile2,"Android O", "Hello Android O",false));


        mAdapter = new FollowersAdapter(FollowersActivity.this, modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new FollowersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, FolowersModel model) {

                //handle item click events here
                Toast.makeText(FollowersActivity.this, "Hey " + model.getUserName(), Toast.LENGTH_SHORT).show();


            }
        });


    }


}
