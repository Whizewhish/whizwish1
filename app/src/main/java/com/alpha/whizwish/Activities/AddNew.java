package com.alpha.whizwish.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.alpha.whizwish.Camera.CameraActivity;
import com.alpha.whizwish.Camera.PicturePreview;
import com.alpha.whizwish.Fragments.AddToWishList;
import com.alpha.whizwish.Fragments.PostOnFriendBoard;
import com.alpha.whizwish.Fragments.ShareGift;
import com.alpha.whizwish.Fragments.ShareMemory;
import com.alpha.whizwish.R;

import java.util.ArrayList;
import java.util.List;
public class AddNew extends AppCompatActivity {



    private int[] tabIcons = {
            R.drawable.share_moment_tab_icon,
            R.drawable.wishboard_tab_icon,
            R.drawable.wishlist_tab_icon,
            R.drawable.gift_tab_icon

    };


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView toolbarTitle;
    String photo_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.sharmoment);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        toolbarTitle.setText(R.string.sharmoment);
                        break;
                    case 1:
                        toolbarTitle.setText("Post on friend's wish board");
                        break;
                    case 2:
                        toolbarTitle.setText("Add to your wish list");
                        break;
                    case 3:
                        toolbarTitle.setText("Share your Gift");
                        break;
                    default:
                        toolbarTitle.setText("Share your moment");
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


         photo_path = getIntent().getStringExtra(CameraActivity.IMAGE_PATH);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ShareMemory(), "ONE");
        adapter.addFragment(new PostOnFriendBoard(), "TWO");
        adapter.addFragment(new AddToWishList(), "THREE");
        adapter.addFragment(new ShareGift(), "Four");
        viewPager.setAdapter(adapter);


    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


    public String getImageData(){

        return photo_path;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(AddNew.this, GenieActivity.class);
        startActivity(i);
    }
}
