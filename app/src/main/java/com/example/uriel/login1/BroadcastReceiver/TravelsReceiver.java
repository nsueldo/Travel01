package com.example.uriel.login1.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.uriel.login1.Utilities.checkTravels;

/**
 * Created by Sueldo on 1/12/2016.
 */

public class TravelsReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "CHECK_TRAVELS";

    // Triggered by the Alarm periodically (starts the service to run task)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, checkTravels.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }
}
