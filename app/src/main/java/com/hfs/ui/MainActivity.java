package com.hfs.ui;

import android.content.Intent;
import android.os.Bundle;

import android.content.Intent;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import com.google.android.gms.tasks.Task;

import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    //private Button button1; //Button for setting future activities from homepage
    //private Button button2; //Button for adding food items from homepage

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment navHomeFragment;
    private FoodHistoryFragment navFoodHistoryFragment;
    private ActivityHistoryFragment navActivityHistoryFragment;

    private FragmentContainerView fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        btnSignUp = findViewById(R.id.button);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);

	/* TODO - Code below is for the home page fragment.
        setContentView(R.layout.activity_homepage);

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
        */

/*
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
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
