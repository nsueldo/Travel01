package com.example.uriel.login1.Activities;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
        setScreen();
    }

    public void lockTravel(View view) {
        blockTravel blocktravel = new blockTravel();
        blocktravel.execute("");
    }

    public void changeTravel(View view){
        DataBaseStatements db = new DataBaseStatements();
        Toast.makeText(this, "Nada", Toast.LENGTH_SHORT).show();
    }

    public void gotoMenu(View view){
        Intent activity_menu =new Intent(select.this,options.class);
        startActivity(activity_menu);
        finish();
    }

    public void gotoExit(View view){
        finish();
    }

    private void setScreen(){
        //Get UI Elements
        tw_source = (TextView)findViewById(R.id.tw_source);
        tw_target = (TextView)findViewById(R.id.tw_target);
        tw_date = (TextView)findViewById(R.id.tw_date);
        tw_time= (TextView)findViewById(R.id.tw_time);
        //Get Travel Data from Notification Intent
        tbl_travelsAvaliables = (ArrayList<String[]>) getIntent().getSerializableExtra("tbl_travels");
        //Obtain Notification State
        NotificationManager nm_travels = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //Close Notification
        nm_travels.cancel(getIntent().getExtras().getInt("notifyID"));

        //Get ID travel and set in id_travel
        id_travel = tbl_travelsAvaliables.get(0)[0];

        //Set Text Views Data
        tw_source.setText(tbl_travelsAvaliables.get(0)[1]);
        tw_target.setText(tbl_travelsAvaliables.get(0)[2]);
        tw_date.setText(tbl_travelsAvaliables.get(0)[3]);
        tw_time.setText(tbl_travelsAvaliables.get(0)[4]);
        tbl_travelsAvaliables.removeAll(tbl_travelsAvaliables);
    }

    public class blockTravel extends AsyncTask<String,String,String> {
        //Declaration
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            //Start Progress Bar
            progressDialog = new ProgressDialog(select.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Seleccionando viaje");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        //Show Toast with the Return Message
        @Override
        protected void onPostExecute(String r) {
            progressDialog.dismiss();
            Toast.makeText(select.this,r,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            //Select current travel in Database
            DataBaseStatements db = new DataBaseStatements();

            db.lockTravel(id_travel);
            return db.message;
        }
    }
}

