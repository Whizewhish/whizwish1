package com.alpha.whizwish.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alpha.whizwish.Adapters.TimelineAdapter;
import com.alpha.whizwish.Models.TimelineModel;
import com.alpha.whizwish.R;

import java.util.ArrayList;
import java.util.List;

public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private View shadow;
    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private List<TimelineModel> timelineModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        shadow = (View) findViewById(R.id.shadow);
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            shadow.setVisibility(View.GONE);
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {

            shadow.setVisibility(View.VISIBLE);
            decor.setSystemUiVisibility(0);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
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
        new loadTimeline().execute();


    }

    private class loadTimeline extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            timelineModels = new ArrayList<>();
            timelineModels.add(new TimelineModel("John Alex", R.drawable.profile5, "New York", "42 minutes ago",R.drawable.post3, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 35));
            timelineModels.add(new TimelineModel("Patrecia Wagner", R.drawable.profile6, "New York", "52 minutes ago",R.drawable.post4, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 54));
            timelineModels.add(new TimelineModel("John Alex", R.drawable.profile5, "New York", "21 minutes ago",R.drawable.post5, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 7));
            timelineModels.add(new TimelineModel("Patrecia Wagner", R.drawable.profile5, "New York", "27 minutes ago",R.drawable.post1, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 87));
            timelineModels.add(new TimelineModel("Patrecia Wagner", R.drawable.profile7, "New York", "42 minutes ago",R.drawable.post5, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 1));
            timelineModels.add(new TimelineModel("John Alex", R.drawable.profile1, "New York", "42 minutes ago",R.drawable.post4, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 33));
            timelineModels.add(new TimelineModel("Patrecia Wagner", R.drawable.profile2, "New York", "42 minutes ago",R.drawable.post2, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 55));
            timelineModels.add(new TimelineModel("John Alex", R.drawable.profile5, "New York", "42 minutes ago",R.drawable.post3, "Lorem Ipsum Dolar Amet", "Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet Lorem Ipsum Dolar Amet", 24));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TimelineAdapter timelineAdapter= new TimelineAdapter(timelineModels, getApplicationContext());
            recyclerView.setAdapter(timelineAdapter);
            timelineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(TimeLineActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
