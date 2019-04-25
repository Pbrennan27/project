package com.assignment.panos.quizzz;

import android.app.Activity;

import android.content.BroadcastReceiver;

import android.content.ComponentName;

import android.content.Context;

import android.content.Intent;

import android.media.MediaPlayer;

import android.media.Ringtone;

import android.media.RingtoneManager;

import android.net.Uri;

import android.support.v4.content.WakefulBroadcastReceiver;

import android.widget.Toast;



public class AlarmReceiver extends WakefulBroadcastReceiver // BroadcastReceiver

{



    @Override

    public void onReceive(final Context context, Intent intent) {

        // this will update the UI with message

        try {

            AlarmActivity inst = AlarmActivity.instance();

            inst.setAlarmText("Alarm! Wake up! Wake up!");



            // this will sound the alarm tone

            // this will sound the alarm once, if you wish to

            // raise alarm in loop continuously then use MediaPlayer and



            Uri alarmUri = RingtoneManager

                    .getDefaultUri(RingtoneManager.TYPE_ALARM);

            if (alarmUri == null) {

                alarmUri = RingtoneManager

                        .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            }

            Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);

            ringtone.play();
           // ringtone.stop();


            // this will send a notification message

            ComponentName comp = new ComponentName(context.getPackageName(),

                    AlarmService.class.getName());

            intent.setComponent(comp);



            // If extended by BroadcastReceiver class then comment this code

            startWakefulService(context, (intent.setComponent(comp)));





            setResultCode(Activity.RESULT_OK);

        } catch (Exception ex) {



        }

    }

}