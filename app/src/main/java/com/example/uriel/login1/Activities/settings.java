package com.example.uriel.login1.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.uriel.login1.R;
import com.example.uriel.login1.Utilities.alarmClass;
import com.example.uriel.login1.Utilities.sharedPreferences;

public class settings extends AppCompatActivity {
    //UI Declarations
    Switch notification;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Set Screen Settings
        setSettings();
        //Activate Action "On Checked Changed" for Button Switch
        status = (TextView) findViewById(R.id.status);
        notification = (Switch) findViewById(R.id.notification);
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    //Enable Travel Notifications
                    alarmClass.setNotifications(getApplicationContext());
                    status.setText("Notificaciones de Viajes: Prendidas");
                } else{
                    //Disable Travel Notifications
                    alarmClass.disableNotifications(getApplicationContext());
                    status.setText("Notificaciones de Viajes: Apagadas");
                }
                //Save Travel Notifications Settings in Shared Preferences
                sharedPreferences.saveSettings(getApplicationContext(), isChecked);
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
