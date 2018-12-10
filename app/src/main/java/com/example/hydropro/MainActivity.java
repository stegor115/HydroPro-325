package com.example.hydropro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private int refillCounter = 0;
    private int increment = 1; //Set to 1 by default

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("Home");
                    return true;
                case R.id.navigation_milestones:
                    mTextMessage.setText("Milestones");
                    //Load MilestoneActivity
                    startActivity(new Intent(MainActivity.this, MilestoneActivity.class));
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText("Settings");
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton button = findViewById(R.id.refill);
        final Button bottle_default = findViewById(R.id.bottle_default);
        final Button bottle_big_default = findViewById(R.id.bottle_big_default);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                refillCounter += increment;
                EditText textView = (EditText) MainActivity.this.findViewById(R.id.counterLabel);
                textView.setText(String.valueOf(refillCounter));
            }
        });

        //These are hardcoded buttons simply for the presentation
        bottle_default.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                increment = 1;
                EditText bottleLabel = (EditText) MainActivity.this.findViewById(R.id.bottleLabel);
                bottleLabel.setText("Current Bottle: Default");
            }
        });

        bottle_big_default.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                increment = 2;
                EditText bottleLabel = (EditText) MainActivity.this.findViewById(R.id.bottleLabel);
                bottleLabel.setText("Current Bottle: Big Default");
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
