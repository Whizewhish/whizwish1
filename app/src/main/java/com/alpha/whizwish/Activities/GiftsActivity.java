package com.alpha.whizwish.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alpha.whizwish.Adapters.GiftsAdapter;
import com.alpha.whizwish.Models.GiftsModel;
import com.alpha.whizwish.R;

import java.util.ArrayList;
import java.util.List;

public class GiftsActivity extends AppCompatActivity {

    private RecyclerView giftsRecycler;
    private List<GiftsModel> mList;
    private TextView toolbarTitle;
    private View shadow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);

        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Gifts");

        shadow = (View) findViewById(R.id.shadow);
        View decor = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            shadow.setVisibility(View.GONE);
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {

            shadow.setVisibility(View.VISIBLE);
            decor.setSystemUiVisibility(0);
        }
        giftsRecycler = (RecyclerView) findViewById(R.id.giftsRecycler);
        giftsRecycler.setLayoutManager(new LinearLayoutManager(this));
        giftsRecycler.setHasFixedSize(true);
        new loadGifts().execute();

    }

    private class loadGifts extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            mList =new ArrayList<>();
            mList.add(new GiftsModel("Patrecia Wagner", R.drawable.profile5, R.drawable.shoes,false, 0));
            mList.add(new GiftsModel("Peter Alex", R.drawable.profile4, R.drawable.shoes,true, 10));
            mList.add(new GiftsModel("Janathon Craft", R.drawable.profile1, R.drawable.shoes,true, 3));
            mList.add(new GiftsModel("Rasendel Roberston", R.drawable.profile5, R.drawable.shoes,true, 5));
            mList.add(new GiftsModel("Peter Alex", R.drawable.profile5, R.drawable.shoes,true, 3));
            mList.add(new GiftsModel("Patrecia Wagner", R.drawable.profile8, R.drawable.shoes,false, 0));
            mList.add(new GiftsModel("Janathon Craft", R.drawable.profile9, R.drawable.shoes,true, 6));
            mList.add(new GiftsModel("Patrecia Wagner", R.drawable.profile6, R.drawable.shoes,true, 6));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            GiftsAdapter giftsAdapter = new GiftsAdapter(mList, getApplicationContext());
            giftsRecycler.setAdapter(giftsAdapter);
            giftsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(GiftsActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
