package com.alpha.whizwish.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import android.support.v4.widget.SwipeRefreshLayout;

import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.support.v7.widget.Toolbar;

import com.alpha.whizwish.Adapters.WhatsOnAdapter;
import com.alpha.whizwish.R;
import com.alpha.whizwish.Models.WhatsOnModel;


public class WhatsOnActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private View shadow;
    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private WhatsOnAdapter mAdapter;

    private ArrayList<WhatsOnModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_on);
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
                }, 3000);

            }
        });

    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
    }



    private void setAdapter() {


        modelList.add(new WhatsOnModel(R.drawable.profile1,"Android",R.drawable.post1));
        modelList.add(new WhatsOnModel(R.drawable.profile2,"Beta",R.drawable.post2));
        modelList.add(new WhatsOnModel(R.drawable.profile3,"Cupcake",R.drawable.post3));
        modelList.add(new WhatsOnModel(R.drawable.profile4,"Donut",R.drawable.post4));
        modelList.add(new WhatsOnModel(R.drawable.profile5,"Eclair",R.drawable.post5));
        modelList.add(new WhatsOnModel(R.drawable.profile6,"Froyo",R.drawable.post3));
        modelList.add(new WhatsOnModel(R.drawable.profile7,"Gingerbread",R.drawable.post1));
        modelList.add(new WhatsOnModel(R.drawable.profile8,"Hello Honeycomb",R.drawable.post5));
        modelList.add(new WhatsOnModel(R.drawable.profile9,"Ice Cream Sandwich",R.drawable.post4));
        modelList.add(new WhatsOnModel(R.drawable.profile10,"Jelly Bean",R.drawable.post2));
        modelList.add(new WhatsOnModel(R.drawable.profile11,"Hello KitKat",R.drawable.post1));
        modelList.add(new WhatsOnModel(R.drawable.profile1,"Hello Lollipop",R.drawable.post3));
        modelList.add(new WhatsOnModel(R.drawable.profile4,"Hello Marshmallow",R.drawable.post5));
        modelList.add(new WhatsOnModel(R.drawable.profile7,"Hello Nougat",R.drawable.post1));
        modelList.add(new WhatsOnModel(R.drawable.profile2, "Hello Android O",R.drawable.post4));


        mAdapter = new WhatsOnAdapter(WhatsOnActivity.this, modelList);

        recyclerView.setHasFixedSize(true);


        final GridLayoutManager layoutManager = new GridLayoutManager(WhatsOnActivity.this, 3);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new WhatsOnAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, WhatsOnModel model) {

                //handle item click events here
                Toast.makeText(WhatsOnActivity.this, "Hey " + model.getUserName(), Toast.LENGTH_SHORT).show();


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(WhatsOnActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
