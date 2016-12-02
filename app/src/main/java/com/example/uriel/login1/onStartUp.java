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
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast..
        //throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(context, "Ha conectado el cargador.", Toast.LENGTH_SHORT).show();
        //Intent servicio = new Intent(context,BackgroudService.class);
        //context.startService(servicio);
        Intent inten = new Intent(context, TravelsReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(context, TravelsReceiver.REQUEST_CODE,
                inten, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every 5 seconds
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                50000L, pIntent);
    }
}

