package com.alpha.whizwish.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.whizwish.Adapters.SelectUsersAdapter;
import com.alpha.whizwish.Models.SelectUsersModel;
import com.alpha.whizwish.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.suke.widget.SwitchButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import info.guardianproject.netcipher.NetCipher;
import okhttp3.OkHttpClient;

public class StoreActivity extends AppCompatActivity {
    private String extracted_url;


    public static final String TO_PROFILE = "Comming From store";
    private WebView webView;
    private ImageView reload;
    // private String TAG = BrowserActivity.class.getSimpleName();
    private String url =
            "https://www.adidas.com/us/";
    private ProgressBar progressBar;
    private float m_downX;
    private Button getWish, friendwish, mywishlist, post, cancel;
    private com.suke.widget.SwitchButton turnOnOff;
    private TextView product_title, product_cost,privateSwitchText, aboveRecyclerText;
    private LinearLayout recyclerContainer;
    private RecyclerView select_users;
    private boolean isMyWishList;
    private ImageView product_image, goBack;
    Dialog storeDialog;
    private List<SelectUsersModel> modelList;
    String title , cost, image_url;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);


        webView = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        reload = (ImageView) findViewById(R.id.reload);
        getWish = (Button) findViewById(R.id.getWish);
        storeDialog = new Dialog(this);
        storeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        storeDialog.setCancelable(false);
        storeDialog.setContentView(R.layout.store_picked_item);
        product_title = (TextView) storeDialog.findViewById(R.id.store_item_title);
        product_cost = (TextView) storeDialog.findViewById(R.id.store_item_cost) ;
        product_image = (ImageView) storeDialog.findViewById(R.id.store_item_image);
        friendwish = (Button) storeDialog.findViewById(R.id.friendwish);
        mywishlist = (Button) storeDialog.findViewById(R.id.mywishlist);
        post = (Button) storeDialog.findViewById(R.id.post);
        cancel = (Button) storeDialog.findViewById(R.id.cancel);
        turnOnOff = (com.suke.widget.SwitchButton) storeDialog.findViewById(R.id.turnOnOff);
        privateSwitchText = (TextView) storeDialog.findViewById(R.id.privateSwitchText) ;
        aboveRecyclerText = (TextView) storeDialog.findViewById(R.id.aboveRecyclerText) ;
        recyclerContainer = (LinearLayout) storeDialog.findViewById(R.id.recyclerContainer);
        select_users = (RecyclerView) storeDialog.findViewById(R.id.select_users);
        select_users.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        select_users.setHasFixedSize(true);
        goBack = (ImageView)findViewById(R.id.goBack);
        new loadUsers().execute();
         progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        getWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extracted_url = webView.getUrl();
               new extractData().execute();
            }
        });
        initWebView();
        webView.loadUrl(url);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();

            }
        });

        if (isMyWishList){
            aboveRecyclerText.setText(getResources().getString(R.string.add_people_who_can_see_this_post));
            privateSwitchText.setText(getResources().getString(R.string.your_post_is_privte_it_will_be_visible_to_the_selected_users));
            recyclerContainer.setVisibility(View.VISIBLE);
        } else {
            aboveRecyclerText.setText(getResources().getString(R.string.send_to));
            privateSwitchText.setText(getResources().getString(R.string.your_post_is_privte_it_will_be_visible_to_the_selected_users));
            recyclerContainer.setVisibility(View.VISIBLE);
        }
        mywishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMyWishList){
                    mywishlist.setBackground(getResources().getDrawable(R.drawable.custom_store_dialog_selected_btn));
                    mywishlist.setTextColor(getResources().getColor(R.color.white));
                    friendwish.setBackground(getResources().getDrawable(R.drawable.custom_store_dialog_unselected_btn));
                    friendwish.setTextColor(getResources().getColor(R.color.grey2));
                    aboveRecyclerText.setText(getResources().getString(R.string.add_people_who_can_see_this_post));
                    isMyWishList = false;

                }
            }
        });

        friendwish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMyWishList){
                    friendwish.setBackground(getResources().getDrawable(R.drawable.custom_store_dialog_selected_btn));
                    friendwish.setTextColor(getResources().getColor(R.color.white));
                    mywishlist.setBackground(getResources().getDrawable(R.drawable.custom_store_dialog_unselected_btn));
                    mywishlist.setTextColor(getResources().getColor(R.color.grey2));
                    aboveRecyclerText.setText(getResources().getString(R.string.send_to));
                    isMyWishList = true;
                }
            }
        });
        turnOnOff.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked){
                    if (isMyWishList){
                        aboveRecyclerText.setText(getResources().getString(R.string.add_people_who_can_see_this_post));
                        privateSwitchText.setText(getResources().getString(R.string.your_post_is_privte_it_will_be_visible_to_the_selected_users));
                        recyclerContainer.setVisibility(View.VISIBLE);
                    } else {
                        aboveRecyclerText.setText(getResources().getString(R.string.send_to));
                        privateSwitchText.setText(getResources().getString(R.string.your_post_is_privte_it_will_be_visible_to_the_selected_users));
                        recyclerContainer.setVisibility(View.VISIBLE);
                    }

                } else {
                    if (isMyWishList){
                        aboveRecyclerText.setText(getResources().getString(R.string.add_people_who_can_see_this_post));
                        privateSwitchText.setText(getResources().getString(R.string.your_post_is_public_it_will_be_visible_to_all_your_followers_and_to_everyone_if_your_account_is_not_privte));
                        recyclerContainer.setVisibility(View.GONE);
                    } else {
                        aboveRecyclerText.setText(getResources().getString(R.string.send_to));
                        privateSwitchText.setText(getResources().getString(R.string.your_post_is_public_it_will_be_visible_to_all_your_followers_and_to_everyone_if_your_account_is_not_privte));
                        recyclerContainer.setVisibility(View.GONE);
                    }

                }
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StoreActivity.this, "Posted To Server Successfully!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(StoreActivity.this, ProfileActivity.class);
                i.putExtra(TO_PROFILE, "open from store");
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeDialog.dismiss();
            }
        });


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(StoreActivity.this, AllStoresActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }

    private class extractData extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();

        }

        @Override
        protected String doInBackground(Void... params) {
            Document doc;
            try {
                HttpsURLConnection netCipherconnection = NetCipher.getHttpsURLConnection(extracted_url);
                netCipherconnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(netCipherconnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String stringHTML;
                while ((stringHTML = bufferedReader.readLine()) != null)
                    stringBuilder.append(stringHTML);
                bufferedReader.close();
                doc = Jsoup.parse(String.valueOf(stringBuilder));

                Element product_main = doc.select("img.performance-item").first();
                Element product_cost = doc.select("span.gl-price").first();
                if (product_main != null && product_cost != null){
                    title = product_main.attr("alt");
                    image_url = product_main.attr("src");
                    cost = product_cost.text();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //if you had a ui element, you could display the title
            progressDialog.dismiss();
            if (title != null && cost != null && image_url != null  ){
                product_title.setText(title);
                product_cost.setText(cost);

                OkHttpClient client = new OkHttpClient();
                Picasso picasso = new Picasso.Builder(getApplicationContext())
                        .downloader(new OkHttp3Downloader(client))
                        .build();

                picasso.load(image_url).into(product_image);
               // Picasso.with(getApplicationContext()).setLoggingEnabled(true);
               // Picasso.with(getApplicationContext()).load("https://assets.adidas.com/images/w_600,h_600,f_auto,q_auto,fl_lossy/0fd08b9ebb8b47ffb3c0a8e80104a88e_9366/Ultraboost_Parley_LTD_Shoes_White_B43512_01_standard.jpg").resize(200, 200).placeholder(R.drawable.post4).centerCrop().into(product_image);
                /*new DownloadImageTask(product_image)
                        .execute(image_url);*/

                storeDialog.show();
            }else {
                Toast.makeText(StoreActivity.this, "Please go to the product page first!", Toast.LENGTH_SHORT).show();
            }




        }
    }


        private void initWebView() {
        webView.setWebChromeClient(new MyWebChromeClient(this));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                reload.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                reload.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
                reload.setVisibility(View.VISIBLE);
            }
        });
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getPointerCount() > 1) {
                    //Multi touch detected
                    return true;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // save the x
                        m_downX = event.getX();
                    }
                    break;

                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        // set x so that it doesn't move
                        event.setLocation(m_downX, event.getY());
                    }
                    break;
                }

                return false;
            }
        });
    }


    private class MyWebChromeClient extends WebChromeClient {
        Context context;

        public MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }
    }

    private class loadUsers extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            modelList = new ArrayList<>();
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile2));
            modelList.add(new SelectUsersModel(R.drawable.profile3));
            modelList.add(new SelectUsersModel(R.drawable.profile4));
            modelList.add(new SelectUsersModel(R.drawable.profile5));
            modelList.add(new SelectUsersModel(R.drawable.profile4));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile7));
            modelList.add(new SelectUsersModel(R.drawable.profile9));
            modelList.add(new SelectUsersModel(R.drawable.profile10));
            modelList.add(new SelectUsersModel(R.drawable.profile11));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile3));
            modelList.add(new SelectUsersModel(R.drawable.profile9));
            modelList.add(new SelectUsersModel(R.drawable.profile3));
            modelList.add(new SelectUsersModel(R.drawable.profile8));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile5));
            modelList.add(new SelectUsersModel(R.drawable.profile2));
            modelList.add(new SelectUsersModel(R.drawable.profile7));
            modelList.add(new SelectUsersModel(R.drawable.profile5));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile9));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile2));
            modelList.add(new SelectUsersModel(R.drawable.profile3));
            modelList.add(new SelectUsersModel(R.drawable.profile4));
            modelList.add(new SelectUsersModel(R.drawable.profile5));
            modelList.add(new SelectUsersModel(R.drawable.profile4));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile7));
            modelList.add(new SelectUsersModel(R.drawable.profile9));
            modelList.add(new SelectUsersModel(R.drawable.profile10));
            modelList.add(new SelectUsersModel(R.drawable.profile11));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile3));
            modelList.add(new SelectUsersModel(R.drawable.profile9));
            modelList.add(new SelectUsersModel(R.drawable.profile3));
            modelList.add(new SelectUsersModel(R.drawable.profile8));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile5));
            modelList.add(new SelectUsersModel(R.drawable.profile2));
            modelList.add(new SelectUsersModel(R.drawable.profile7));
            modelList.add(new SelectUsersModel(R.drawable.profile5));
            modelList.add(new SelectUsersModel(R.drawable.profile1));
            modelList.add(new SelectUsersModel(R.drawable.profile9));
            SelectUsersAdapter selectUsersAdapter = new SelectUsersAdapter(modelList, getApplicationContext());
            select_users.setAdapter(selectUsersAdapter);
            selectUsersAdapter.notifyDataSetChanged();
            return null;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
