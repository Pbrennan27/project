package com.assignment.panos.quizzz;



import android.app.IntentService;

import android.app.NotificationManager;

import android.app.PendingIntent;

import android.content.Context;

import android.content.Intent;

import android.support.v4.app.NotificationCompat;



public class AlarmService extends IntentService {

    private NotificationManager alarmNotificationManager;



    public AlarmService() {

        super("AlarmService");

    }



    @Override

    public void onHandleIntent(Intent intent) {

        sendNotification("Wake Up! Wake Up!");

    }



    private void sendNotification(String msg) {

        // NotificationManager class to notify the user of events // that happen. This is how you tell the user that something // has happened in the background.

        alarmNotificationManager = (NotificationManager) this

                .getSystemService(Context.NOTIFICATION_SERVICE);



        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,

                new Intent(this, AlarmActivity.class), 0);



        // set icon, title and message for notification

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(

                this).setContentTitle("Alarm")

                .setSmallIcon(R.drawable.images)

                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))

                .setContentText(msg);



        alamNotificationBuilder.setContentIntent(contentIntent);

        alarmNotificationManager.notify(1, alamNotificationBuilder. build());



    }

}