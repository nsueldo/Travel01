package com.example.uriel.login1.Utilities;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import com.example.uriel.login1.Activities.select;
import com.example.uriel.login1.MySQL.DataBaseStatements;
import java.util.ArrayList;


public class checkTravels extends IntentService {

    public checkTravels() {
        super("checkTravels");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DataBaseStatements db = new DataBaseStatements();
        ArrayList<String[]> tbl_travels = db.travelsAvaliable();

        Intent transfer = new Intent(this,select.class);
        transfer.putExtra("notifyID", 12345);
        transfer.putExtra("tbl_travels",tbl_travels);
        PendingIntent activity_select = PendingIntent.getActivity(this, 0, transfer, 0);

        if (db.status == "OK"){
            NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification noti = new NotificationCompat.Builder(this)
                    .setContentIntent(activity_select)
                    .setContentTitle("Viajes")
                    .setContentText(db.message)
                    .setVibrate(new long[] {100, 250, 100, 500})
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setWhen(System.currentTimeMillis())
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .build();
            nManager.notify(12345, noti);
        }
    }
}
