package com.example.hydropro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import android.app.AlarmManager;
import android.widget.Toast;

public class NotificationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    int hour = 0;
    int minute = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(NotificationActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_milestones:
                    startActivity(new Intent(NotificationActivity.this, MilestoneActivity.class));
                    return true;
                case R.id.navigation_settings:
                    startActivity(new Intent(NotificationActivity.this, SettingActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final Button bSave = findViewById(R.id.buttonSave);
        final Spinner dropDownHours = findViewById(R.id.dropdown_hour);
        Spinner dropDownMins = findViewById(R.id.dropdown_minute);

        ArrayAdapter<CharSequence> hoursAdapter = ArrayAdapter.createFromResource(this,R.array.hours,android.R.layout.simple_spinner_item);
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownHours.setAdapter(hoursAdapter);
        dropDownHours.setSelection(0);
        dropDownHours.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> minsAdapter = ArrayAdapter.createFromResource(this,R.array.minutes,android.R.layout.simple_spinner_item);
        minsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownMins.setAdapter(minsAdapter);
        dropDownMins.setSelection(0);
        dropDownMins.setOnItemSelectedListener(this);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = Integer.parseInt(dropDownHours.getSelectedItem().toString());
                minute = Integer.parseInt(dropDownHours.getSelectedItem().toString());
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 1);
                Intent intent = new Intent(getApplicationContext(), NotifyReciever.class);
                //intent.setAction("MY_NOTIFICATION_MESSAGE");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

                startActivity(new Intent(NotificationActivity.this, SettingActivity.class)); //Go back to settings
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_settings);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text  = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
