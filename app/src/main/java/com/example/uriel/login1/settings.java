package com.example.uriel.login1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.Gravity;

import android.widget.Toast;

public class settings extends AppCompatActivity {
    Switch notification;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setSettings();

        //Set Action "On Checked Changed" for Button Switch
        status = (TextView) findViewById(R.id.status);
        notification = (Switch) findViewById(R.id.notification);
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    status.setText("Switch is currently ON");
                } else{
                    //Get Alarm System Servide
                    AlarmManager aManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    //Get Intent TravelsReceiver
                    Intent intent = new Intent(getBaseContext(), TravelsReceiver.class);
                    //Build Pending Intent from the Intent
                    PendingIntent pIntent = PendingIntent.getBroadcast(getApplicationContext(),
                            TravelsReceiver.REQUEST_CODE, intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    //Cancel de Pending Item of Alarm Manager
                    aManager.cancel(pIntent);
                    status.setText("Switch is currently OFF");
                }
            }
       });
    }

    private void setSettings(){
        //UI Declarations
        boolean checkNotification;
        //Get Shared Preferences of Button Switch
        checkNotification = sharedPreferences.getSettings(getApplicationContext());
        //Set Preferences in the Screen
        notification = (Switch) findViewById(R.id.notification);
        notification.setChecked(checkNotification);
    }
}
