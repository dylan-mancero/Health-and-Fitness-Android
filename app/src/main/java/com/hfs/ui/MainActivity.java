package com.hfs.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hfs.ui.fragments.ActivityHistoryFragment;
import com.hfs.ui.fragments.AddFoodItem;
import com.hfs.ui.fragments.AddFutureActivity;
import com.hfs.ui.fragments.FoodHistoryFragment;
import com.hfs.ui.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private LinearLayout mMainPageSlider;

    private HomeFragment navHomeFragment;
    private FoodHistoryFragment navFoodHistoryFragment;
    private ActivityHistoryFragment navActivityHistoryFragment;

    private FragmentContainerView fragmentContainer;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainPageSlider =  findViewById(R.id.home_page_slider);

       // fragmentContainer = findViewById(R.id.fragment_container_view);
       // getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, new HomeFragment());

        navHomeFragment = new HomeFragment();
        navFoodHistoryFragment = new FoodHistoryFragment();
        navActivityHistoryFragment = new ActivityHistoryFragment();

        //setFragment(navHomeFragment);

        List<Fragment> list = new ArrayList<>();
        //Position of where the fragment is added might affect which fragment shows on slide first...???
        list.add(new AddFutureActivity());
        list.add(new HomeFragment());
        list.add(new AddFoodItem());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // TODO - fix disappearing layout on double click off nav buttons.
                switch (item.getItemId()){

                    case R.id.nav_home :
                        mMainFrame.removeAllViewsInLayout();
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        mMainFrame.addView(mMainPageSlider);
                        //setFragment(navHomeFragment);
                        return true;

                    case R.id.nav_foodHistory :
                        mMainFrame.removeAllViews();
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(navFoodHistoryFragment);
                        return true;

                    case R.id.nav_activityHistory :
                        mMainFrame.removeAllViews();
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(navActivityHistoryFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

        /*
        TODO - Add logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToLogin = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intToLogin);
            }
        });
        * */

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}
