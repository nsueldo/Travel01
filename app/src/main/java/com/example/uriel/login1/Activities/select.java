package com.example.uriel.login1.Activities;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.uriel.login1.MySQL.DataBaseStatements;
import com.example.uriel.login1.R;

import java.util.ArrayList;

public class select extends AppCompatActivity {

    ArrayList<String[]> tbl_travelsAvaliables;
    TextView tw_source, tw_target, tw_date, tw_time;
    String id_travel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //Get UI Elements
        tw_source = (TextView)findViewById(R.id.tw_source);
        tw_target = (TextView)findViewById(R.id.tw_target);
        tw_date = (TextView)findViewById(R.id.tw_date);
        tw_time= (TextView)findViewById(R.id.tw_time);

        //Obtenemos la tabla de viajes disponibles
        tbl_travelsAvaliables = (ArrayList<String[]>) getIntent().getSerializableExtra("tbl_travels");
        //Obtenemos el estado de la notificacion
        NotificationManager nm_travels = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //Close Notification
        nm_travels.cancel(getIntent().getExtras().getInt("notifyID"));
        //Set data for TextViews
        id_travel = tbl_travelsAvaliables.get(0)[0];
        tw_source.setText(tbl_travelsAvaliables.get(0)[1]);
        tw_target.setText(tbl_travelsAvaliables.get(0)[2]);
        tw_date.setText(tbl_travelsAvaliables.get(0)[3]);
        tw_time.setText(tbl_travelsAvaliables.get(0)[4]);

    }

    public void lockTravel(View view) {
        DataBaseStatements db = new DataBaseStatements();
        db.lockTravel(id_travel);

        if (db.status == "OK") {
            Toast.makeText(this, db.message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, db.message, Toast.LENGTH_SHORT).show();
        }
    }

    public void changeTravel(View view){
        DataBaseStatements db = new DataBaseStatements();
        Toast.makeText(this, "Nada", Toast.LENGTH_SHORT).show();
    }


}
