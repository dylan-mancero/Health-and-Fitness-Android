package com.hfs.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private Button button1; //Button for setting future activities from homepage
    private Button button2; //Button for adding food items from homepage

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private FoodHistoryFragment foodHistoryFragment;
    private ActivityHistoryFragment activityHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        foodHistoryFragment = new FoodHistoryFragment();
        activityHistoryFragment = new ActivityHistoryFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.nav_home :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_foodHistory :
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(foodHistoryFragment);
                        return true;

                    case R.id.nav_activityHistory :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(activityHistoryFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });

        //Associating button1&2 with relevant button id's
        button1 = findViewById(R.id.setFutureActivity);
        button2 = findViewById(R.id.setFoodItem);

        //Setting the action that needs to be performed after specific button click from homepage
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openSetFutureActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openSetFoodItem();
            }
        });

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    public void openSetFutureActivity() {
        Intent intent = new Intent(this, addFutureActivity.class);
        startActivity(intent);
    }

    public void openSetFoodItem() {
        Intent intent = new Intent(this, addFood.class);
        startActivity(intent);
    }

}
