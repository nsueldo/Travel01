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
        //Set Alarm "Notifications"
    }
}

