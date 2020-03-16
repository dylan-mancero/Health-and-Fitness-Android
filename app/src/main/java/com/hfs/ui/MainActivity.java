package com.hfs.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1; //Button for setting future activities from homepage
    private Button button2; //Button for adding food items from homepage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

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

    public void openSetFutureActivity() {
        Intent intent = new Intent(this, addFutureActivity.class);
        startActivity(intent);
    }

    public void openSetFoodItem() {
        Intent intent = new Intent(this, addFood.class);
        startActivity(intent);
    }

}
