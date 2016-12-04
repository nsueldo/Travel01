package com.example.uriel.login1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Uriel on 12/3/2016.
 */

public class alarmClass {

    static public void setNotifications(Context context){
        //Instance Travels Receiver Class
        Intent iTravelReceiver = new Intent(context, TravelsReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(context,
                TravelsReceiver.REQUEST_CODE, iTravelReceiver, PendingIntent.FLAG_UPDATE_CURRENT);
        //Get Current Time in Milliseconds
        long firstMillis = System.currentTimeMillis();
        //Starting of Current Time -> Set Alarm every 45 Seconds
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                45000L, pIntent);
    }

    static public void disableNotifications(Context context){
        //Get Alarm System Servide
        AlarmManager aManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        //Get Intent TravelsReceiver
        Intent intent = new Intent(context, TravelsReceiver.class);
        //Build Pending Intent from the Intent
        PendingIntent pIntent = PendingIntent.getBroadcast(context, TravelsReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //Cancel de Pending Item of Alarm Manager
        aManager.cancel(pIntent);
    }
}
