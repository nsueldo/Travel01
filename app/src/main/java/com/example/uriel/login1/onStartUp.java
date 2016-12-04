package com.example.uriel.login1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.widget.Toast;

public class onStartUp extends BroadcastReceiver {


    public onStartUp() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //Show Toast when the charger connects
        Toast.makeText(context, "Ha conectado el cargador.", Toast.LENGTH_SHORT).show();
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
}

