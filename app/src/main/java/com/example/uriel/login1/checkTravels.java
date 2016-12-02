package com.example.uriel.login1;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


public class checkTravels extends IntentService {

    public checkTravels() {
        super("checkTravels");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Do the task here
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder constructor = new NotificationCompat.Builder(
                getBaseContext())
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Viajes")
                .setContentText("Se detectaron viajes disponibles")
                .setWhen(System.currentTimeMillis());

        nManager.notify(12345, constructor.build());
    }
}
