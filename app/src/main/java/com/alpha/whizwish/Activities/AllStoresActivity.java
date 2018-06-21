package com.alpha.whizwish.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alpha.whizwish.Adapters.StoreItemAdapter;
import com.alpha.whizwish.Models.StoreModel;
import com.alpha.whizwish.R;

import java.util.ArrayList;
import java.util.List;


public class AllStoresActivity extends AppCompatActivity {

    private TextView toolbarTitle;
    private RecyclerView featuredRecycler, viewedRecycler, generalRecycler;
    private List<StoreModel> featuredList, viewedList, generalList;
    private ProgressDialog progressDialog;
    private ImageView goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stores);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.Stores);

        generalRecycler = (RecyclerView)findViewById(R.id.generalRecycler);
        viewedRecycler = (RecyclerView)findViewById(R.id.viewedRecycler);
        featuredRecycler = (RecyclerView)findViewById(R.id.featuredRecycler);

        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        viewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        generalRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        viewedRecycler.setHasFixedSize(true);
        generalRecycler.setHasFixedSize(true);
        featuredRecycler.setHasFixedSize(true);


        goBack = (ImageView) findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(AllStoresActivity.this, GenieActivity.class);
                startActivity(i);
            }
        });


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.getting_stores));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        new loadStores().execute();




    }


    private class loadStores extends AsyncTask<Void, Void ,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            featuredList = new ArrayList<>();
            viewedList = new ArrayList<>();
            generalList = new ArrayList<>();

            featuredList.add(new StoreModel(R.drawable.nike, "Nike"));
            featuredList.add(new StoreModel(R.drawable.puma, "Puma"));
            featuredList.add(new StoreModel(R.drawable.hm, "hm"));
            featuredList.add(new StoreModel(R.drawable.nike, "Nike"));
            featuredList.add(new StoreModel(R.drawable.puma, "Puma"));
            featuredList.add(new StoreModel(R.drawable.hm, "hm"));
            featuredList.add(new StoreModel(R.drawable.nike, "Nike"));
            featuredList.add(new StoreModel(R.drawable.puma, "Puma"));
            featuredList.add(new StoreModel(R.drawable.hm, "hm"));

            viewedList.add(new StoreModel(R.drawable.nike, "Nike"));
            viewedList.add(new StoreModel(R.drawable.puma, "Puma"));
            viewedList.add(new StoreModel(R.drawable.hm, "hm"));
            viewedList.add(new StoreModel(R.drawable.nike, "Nike"));
            viewedList.add(new StoreModel(R.drawable.puma, "Puma"));
            viewedList.add(new StoreModel(R.drawable.hm, "hm"));
            viewedList.add(new StoreModel(R.drawable.nike, "Nike"));
            viewedList.add(new StoreModel(R.drawable.puma, "Puma"));
            viewedList.add(new StoreModel(R.drawable.hm, "hm"));


            generalList.add(new StoreModel(R.drawable.nike, "Nike"));
            generalList.add(new StoreModel(R.drawable.puma, "Puma"));
            generalList.add(new StoreModel(R.drawable.hm, "hm"));
            generalList.add(new StoreModel(R.drawable.nike, "Nike"));
            generalList.add(new StoreModel(R.drawable.puma, "Puma"));
            generalList.add(new StoreModel(R.drawable.hm, "hm"));
            generalList.add(new StoreModel(R.drawable.nike, "Nike"));
            generalList.add(new StoreModel(R.drawable.puma, "Puma"));
            generalList.add(new StoreModel(R.drawable.hm, "hm"));
            generalList.add(new StoreModel(R.drawable.nike, "Nike"));
            generalList.add(new StoreModel(R.drawable.puma, "Puma"));
            generalList.add(new StoreModel(R.drawable.hm, "hm"));
            generalList.add(new StoreModel(R.drawable.nike, "Nike"));
            generalList.add(new StoreModel(R.drawable.puma, "Puma"));
            generalList.add(new StoreModel(R.drawable.hm, "hm"));
            generalList.add(new StoreModel(R.drawable.nike, "Nike"));
            generalList.add(new StoreModel(R.drawable.puma, "Puma"));
            generalList.add(new StoreModel(R.drawable.hm, "hm"));
            generalList.add(new StoreModel(R.drawable.nike, "Nike"));
            generalList.add(new StoreModel(R.drawable.puma, "Puma"));
            generalList.add(new StoreModel(R.drawable.hm, "hm"));

            StoreItemAdapter featured = new StoreItemAdapter(getApplicationContext(), featuredList);
            StoreItemAdapter viewed = new StoreItemAdapter(getApplicationContext(), viewedList);
            StoreItemAdapter  general = new StoreItemAdapter(getApplicationContext(), generalList);

            featuredRecycler.setAdapter(featured);
            viewedRecycler.setAdapter(viewed);
            generalRecycler.setAdapter(general);

            featured.notifyDataSetChanged();

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(AllStoresActivity.this, GenieActivity.class);
        startActivity(i);
    }
}
