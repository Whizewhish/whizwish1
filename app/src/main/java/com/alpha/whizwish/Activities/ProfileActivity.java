package com.alpha.whizwish.Activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alpha.whizwish.Adapters.MemoriesProfileAdapter;
import com.alpha.whizwish.Adapters.WhatsOnAdapter;
import com.alpha.whizwish.Adapters.WishBoardAdapter;
import com.alpha.whizwish.Adapters.WishListAdapter;
import com.alpha.whizwish.Models.MemoriesProfileModel;
import com.alpha.whizwish.Models.WhatsOnModel;
import com.alpha.whizwish.Models.WishBoardModel;
import com.alpha.whizwish.Models.WishListModel;
import com.alpha.whizwish.R;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lib.kingja.switchbutton.SwitchMultiButton;

public class ProfileActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.6f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;
    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;
    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private SwitchMultiButton tabs;
    private Button myRec, myWishBord;
    private LinearLayout recyclerLayout;
    private RecyclerView recycler1, recycler2,recycler3;
    private MemoriesProfileAdapter mAdapterMemories;
    private WishListAdapter mAdapterWishList;
    private WishBoardAdapter mAdapterWishBoard;
    private boolean isMyWishboard;

    private ArrayList<MemoriesProfileModel> modelList_memories = new ArrayList<>();
    private ArrayList<WishListModel> modelList_wish_list = new ArrayList<>();
    private ArrayList<WishBoardModel> modelList_wish_board = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bindActivity();
        bindTabs();
        String comeFromWhere = getIntent().getStringExtra(StoreActivity.TO_PROFILE);
        if (comeFromWhere != null){
        if (comeFromWhere.equals("open from store") ){

            tabs.setSelectedTab(1);
            recycler1.setVisibility(View.GONE);
            recycler2.setVisibility(View.VISIBLE);
            recyclerLayout.setVisibility(View.GONE);
        } else {

            tabs.setSelectedTab(0);
            recycler1.setVisibility(View.VISIBLE);
            recycler2.setVisibility(View.GONE);
            recyclerLayout.setVisibility(View.GONE);

        }
        } else {
            tabs.setSelectedTab(0);
            recycler1.setVisibility(View.VISIBLE);
            recycler2.setVisibility(View.GONE);
            recyclerLayout.setVisibility(View.GONE);

        }
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        setAdapeters();
        tabs.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {

                switch (position){
                    case 0:
                        recycler1.setVisibility(View.VISIBLE);
                        recycler2.setVisibility(View.GONE);
                        recyclerLayout.setVisibility(View.GONE);
                        break;
                    case 1:
                        recycler1.setVisibility(View.GONE);
                        recycler2.setVisibility(View.VISIBLE);
                        recyclerLayout.setVisibility(View.GONE);
                        break;
                    case 2:
                        recycler1.setVisibility(View.GONE);
                        recycler2.setVisibility(View.GONE);
                        recyclerLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        recycler1.setVisibility(View.VISIBLE);
                        recycler2.setVisibility(View.GONE);
                        recyclerLayout.setVisibility(View.GONE);

                }
            }
        });


    }

    private void bindTabs() {
        switch (tabs.getSelectedTab()){
            case 0:
                recycler1.setVisibility(View.VISIBLE);
                recycler2.setVisibility(View.GONE);
                recyclerLayout.setVisibility(View.GONE);
                break;
            case 1:
                recycler1.setVisibility(View.GONE);
                recycler2.setVisibility(View.VISIBLE);
                recyclerLayout.setVisibility(View.GONE);
                break;
            case 2:
                recycler1.setVisibility(View.GONE);
                recycler2.setVisibility(View.GONE);
                recyclerLayout.setVisibility(View.VISIBLE);
                break;
            default:
                recycler1.setVisibility(View.VISIBLE);
                recycler2.setVisibility(View.GONE);
                recyclerLayout.setVisibility(View.GONE);

        }
    }

    private void setAdapeters() {

        //setting content for grid memories recycler
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post1));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post2));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post3));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post4));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post5));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post3));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post1));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post5));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post4));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post2));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post1));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post3));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post5));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post1));
        modelList_memories.add(new MemoriesProfileModel(R.drawable.post4));
        mAdapterMemories = new MemoriesProfileAdapter(ProfileActivity.this, modelList_memories);
        recycler1.setHasFixedSize(true);
        recycler1.setAdapter(mAdapterMemories);
        mAdapterMemories.SetOnItemClickListener(new MemoriesProfileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, MemoriesProfileModel model) {
                Toast.makeText(ProfileActivity.this, "position is: " + position, Toast.LENGTH_SHORT).show();
            }
        });


        //setting content for grid wish list recycler
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        modelList_wish_list.add(new WishListModel(R.drawable.post1, "$ 54", "Greece Glasses"));
        mAdapterWishList = new WishListAdapter(ProfileActivity.this, modelList_wish_list);
        recycler2.setHasFixedSize(true);
        recycler2.setAdapter(mAdapterWishList);
        mAdapterWishList.SetOnItemClickListener(new WishListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, WishListModel model) {
                Toast.makeText(ProfileActivity.this, "wish position: " + position, Toast.LENGTH_SHORT).show();
            }
        });


        if (isMyWishboard){

            setUpMyRecAdapter();

        }else {

           setUpMyWishBoardAdapter();
        }




    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle          = (TextView) findViewById(R.id.main_textview_title);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.main_appbar);
        myRec = (Button) findViewById(R.id.myRec);
        myWishBord = (Button) findViewById(R.id.myWishBord);
        tabs = (SwitchMultiButton) findViewById(R.id.tabs);
        recyclerLayout = (LinearLayout) findViewById(R.id.recyclerLayout);
        recycler1 = (RecyclerView)findViewById(R.id.recycler1);


        recycler2 = (RecyclerView)findViewById(R.id.recycler2);
        recycler3 = (RecyclerView)findViewById(R.id.recycler3);
        recycler1.setLayoutManager(new GridLayoutManager(this, 3));
        recycler2.setLayoutManager(new GridLayoutManager(this, 2));
        recycler3.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {

            if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

                if(!mIsTheTitleVisible) {
                    startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                    mToolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                    mIsTheTitleVisible = true;
                }

            } else {

                if (mIsTheTitleVisible) {
                    startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                    mToolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                    mIsTheTitleVisible = false;
                }
            }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
            ? new AlphaAnimation(0f, 1f)
            : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    public void myWishBoard(View view) {


            setUpMyWishBoardAdapter();

    }
    public void myRecommendations(View view) {



            setUpMyRecAdapter();


    }

    private void setUpMyRecAdapter() {

        if (isMyWishboard) {
            myWishBord.setBackground(getResources().getDrawable(R.drawable.btn_follow));
            myWishBord.setTextColor(getResources().getColor(R.color.text_borders_color));
            myRec.setBackground(getResources().getDrawable(R.drawable.btn_followed));
            myRec.setTextColor(getResources().getColor(R.color.white));
            isMyWishboard = false;


            //setting content for grid wish board  for my recomendation button recycler
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            modelList_wish_board.add(new WishBoardModel(R.drawable.profile1, R.drawable.post2, "$ 43", "German Shoes"));
            mAdapterWishBoard = new WishBoardAdapter(ProfileActivity.this, modelList_wish_board);
            recycler3.setHasFixedSize(true);
            recycler3.setAdapter(mAdapterWishBoard);
            mAdapterWishBoard.notifyDataSetChanged();
            mAdapterWishBoard.SetOnItemClickListener(new WishBoardAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, WishBoardModel model) {
                    Toast.makeText(ProfileActivity.this, "wish bord item: " + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


    private void setUpMyWishBoardAdapter() {

        if (!isMyWishboard) {
            myWishBord.setBackground(getResources().getDrawable(R.drawable.btn_followed));
            myWishBord.setTextColor(getResources().getColor(R.color.white));
            myRec.setBackground(getResources().getDrawable(R.drawable.btn_follow));
            myRec.setTextColor(getResources().getColor(R.color.text_borders_color));
            isMyWishboard = true;
        //setting content for grid wish board for my wishboard button  recycler
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile1,R.drawable.post2, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile1,R.drawable.post2, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile1,R.drawable.post2, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile1,R.drawable.post2, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile5,R.drawable.post4, "$ 43", "German Shoes"));
        modelList_wish_board.add(new WishBoardModel(R.drawable.profile1,R.drawable.post2, "$ 43", "German Shoes"));
        mAdapterWishBoard = new WishBoardAdapter(ProfileActivity.this, modelList_wish_board);
        recycler3.setHasFixedSize(true);
        recycler3.setAdapter(mAdapterWishBoard);
        mAdapterWishBoard.notifyDataSetChanged();
        mAdapterWishBoard.SetOnItemClickListener(new WishBoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, WishBoardModel model) {
                Toast.makeText(ProfileActivity.this, "wish bord item: "  + position, Toast.LENGTH_SHORT).show();
            }
        });
    }}


    public void openFollowers(View view) {

        Intent i = new Intent(ProfileActivity.this, FollowersActivity.class);
        startActivity(i);
    }

    public void openFollowing(View view) {
        Intent i = new Intent(ProfileActivity.this, FollowersActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String comeFromWhere = getIntent().getStringExtra(StoreActivity.TO_PROFILE);
        if (comeFromWhere != null){

            if (comeFromWhere.equals("open from store")){
                finish();
                Intent i = new Intent(ProfileActivity.this, StoreActivity.class);
                startActivity(i);
            } else if (comeFromWhere.equals("from timeline")){
                finish();
                Intent i = new Intent(ProfileActivity.this, TimeLineActivity.class);
                startActivity(i);

            } else {
                finish();
                Intent i = new Intent(ProfileActivity.this, GenieActivity.class);
                startActivity(i);

            }
        } else {
            finish();
            Intent i = new Intent(ProfileActivity.this, GenieActivity.class);
            startActivity(i);

        }
    }

    public void goBack(View view) {


        String comeFromWhere = getIntent().getStringExtra(StoreActivity.TO_PROFILE);
        if (comeFromWhere != null){

        if (comeFromWhere.equals("open from store")){
            finish();
            Intent i = new Intent(ProfileActivity.this, StoreActivity.class);
            startActivity(i);
        } else if (comeFromWhere.equals("from timeline")){
            finish();
            Intent i = new Intent(ProfileActivity.this, TimeLineActivity.class);
            startActivity(i);

        } else {
            finish();
            Intent i = new Intent(ProfileActivity.this, GenieActivity.class);
            startActivity(i);

        }
        } else {
            finish();
            Intent i = new Intent(ProfileActivity.this, GenieActivity.class);
            startActivity(i);

        }
    }


}
