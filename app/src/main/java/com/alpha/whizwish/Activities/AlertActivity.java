package com.alpha.whizwish.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alpha.whizwish.Adapters.AlertAdapter;
import com.alpha.whizwish.CustomView.MyDividerItemDecoration;
import com.alpha.whizwish.Models.AlertModel;
import com.alpha.whizwish.R;

import java.util.ArrayList;
import java.util.List;

public class AlertActivity extends AppCompatActivity {
    private View shadow;

    private RecyclerView recyclerView;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Alerts");

        shadow = (View) findViewById(R.id.shadow);
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            shadow.setVisibility(View.GONE);
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {

            shadow.setVisibility(View.VISIBLE);
            decor.setSystemUiVisibility(0);
        }



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        List<AlertModel> list  = new ArrayList<>();
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a second alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer. i told you it will be so cool !"));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer. this is along alert to try if it is taking two lines only"));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer. test alert"));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer. from user one"));
        list.add(new AlertModel(R.drawable.alert_icon, "commented on your photo, hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "user1 likes a photo of you. hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, " user3 accepted your gift. hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, " user 8 sent you a gist, click to view more details. hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));
        list.add(new AlertModel(R.drawable.alert_icon, "hi this is a new alert ! from developer."));

        AlertAdapter alertAdapter = new AlertAdapter(list, getApplicationContext());
        recyclerView.setAdapter(alertAdapter);
        alertAdapter.notifyDataSetChanged();



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(AlertActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
