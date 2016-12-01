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
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, BackgroudService.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 20000, pi); // Millisec * Second * Minute
    }
}

