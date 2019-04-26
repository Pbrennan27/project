package com.assignment.panos.quizzz;
/* Reference https://androidclarified.com/android-example-alarm-manager-complete-working/ */

import android.app.Activity;

import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;

import android.widget.TimePicker;

import android.widget.Toast;

import android.widget.ToggleButton;



import java.util.Calendar;



public class AlarmActivity extends Activity {


    //Button off = (Button) findViewById(R.id.alarmStop); /* Alarm Stop Button*/
  private AlarmManager alarmManager;

    private PendingIntent pendingIntent;

    private TimePicker alarmTimePicker;

    private static AlarmActivity inst;

    private TextView alarmTextView;



    public static AlarmActivity instance() {

        return inst;

    }



    @Override

    public void onStart() {

        super.onStart();

        inst = this;

    }



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer);

        // get view from layout

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);

        alarmTextView = (TextView) findViewById(R.id.alarmText);

        final ToggleButton alarmToggle = (ToggleButton)                       findViewById(R.id.alarmToggle);





        // use alarm service

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // toggle button click listener

        alarmToggle.setOnClickListener(new OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                try {

                    while (alarmToggle.isChecked()) {

                        Toast.makeText(getApplicationContext(), "Alarm On", Toast.LENGTH_SHORT).show();

                        Calendar calendar = Calendar.getInstance();

                        // set selected time from timepicker to calendar

                        calendar.set(Calendar.HOUR_OF_DAY,

                                alarmTimePicker.getCurrentHour());

                        calendar.set(Calendar.MINUTE,

                                alarmTimePicker.getCurrentMinute());



                        Intent myIntent = new Intent(AlarmActivity.this,

                                AlarmReceiver.class);



                        // A PendingIntent specifies an action to take in the
                        // future

                        pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0);



                        // set alarm time

                        alarmManager.set(AlarmManager.RTC,

                                calendar.getTimeInMillis(), pendingIntent);

                    } else {

                        // Cancel alarm

                        alarmManager.cancel(pendingIntent);

                        Toast.makeText(getApplicationContext(), "Alarm Off", Toast.LENGTH_SHORT).show();

                        setAlarmText("");

                    }

                } catch (Exception ex) {
                }



            }

        });
    }



    public void setAlarmText(String alarmText) {

        alarmTextView.setText(alarmText);

    }

}