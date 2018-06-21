package com.alpha.whizwish.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.alpha.whizwish.Camera.CameraActivity;
import com.alpha.whizwish.Camera.VideoActivity;
import com.alpha.whizwish.Models.AlertModel;
import com.alpha.whizwish.R;

import java.util.List;

public class GenieActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;
    List<AlertModel> allItems;
    private GridLayout gridLay;
    private View shadow;
    TextView whatsOn,timeline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genie);
        whatsOn= (TextView) findViewById(R.id.whatsOn);
        timeline = (TextView) findViewById(R.id.timeline);
        timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(GenieActivity.this, TimeLineActivity.class);
                startActivity(i);
            }
        });
        whatsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(GenieActivity.this, WhatsOnActivity.class);
                startActivity(i);
            }
        });
        shadow = (View) findViewById(R.id.shadow);
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            shadow.setVisibility(View.GONE);
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {

            shadow.setVisibility(View.VISIBLE);
            decor.setSystemUiVisibility(0);
        }
        gridLay = (GridLayout) findViewById(R.id.grid);



    }


    public void showAlerts(View view) {

        finish();
        Intent i = new Intent(GenieActivity.this, AlertActivity.class);
        startActivity(i);
    }

    public void openCamera(View view) {

        finish();
        Intent i = new Intent(GenieActivity.this, CameraActivity.class);
        startActivity(i);

    }

    public void recordVideo(View view) {
        finish();
        Intent i = new Intent(GenieActivity.this, VideoActivity.class);
        startActivity(i);

    }

    public void openProfile(View view) {
        finish();
        Intent i = new Intent(GenieActivity.this, ProfileActivity.class);
        startActivity(i);

    }

    public void openAddNew(View view) {
        finish();
        Intent i = new Intent(GenieActivity.this, AddNew.class);
        startActivity(i);
    }

    public void openStores(View view) {
        finish();
        Intent i = new Intent(GenieActivity.this, AllStoresActivity.class);
        startActivity(i);
    }

    public void openGifts(View view) {

        finish();
        Intent i = new Intent(GenieActivity.this, GiftsActivity.class);
        startActivity(i);
    }

    public void openRecord(View view) {
        finish();
        Intent i = new Intent(GenieActivity.this, AudioRecorderActivity.class);
        startActivity(i);
    }
}
