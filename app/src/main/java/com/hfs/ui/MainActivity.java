package com.hfs.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hfs.ui.fragments.ActivityHistoryFragment;
import com.hfs.ui.fragments.FoodHistoryFragment;
import com.hfs.ui.fragments.HomeFragment;
import com.hfs.ui.fragments.PageFragment1;
import com.hfs.ui.fragments.PageFragment2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

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

        fragmentContainer = findViewById(R.id.fragment_container_view);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_view, new HomeFragment());

        navHomeFragment = new HomeFragment();
        navFoodHistoryFragment = new FoodHistoryFragment();
        navActivityHistoryFragment = new ActivityHistoryFragment();

        setFragment(navHomeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_home :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(navHomeFragment);
                        return true;

                    case R.id.nav_foodHistory :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(navFoodHistoryFragment);
                        return true;

                    case R.id.nav_activityHistory :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(navActivityHistoryFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);

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
