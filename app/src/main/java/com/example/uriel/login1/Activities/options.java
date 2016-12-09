package com.example.uriel.login1.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.uriel.login1.MySQL.DataBaseStatements;
import com.example.uriel.login1.R;

import java.util.ArrayList;


public class options extends AppCompatActivity {
    Button btn_show, btn_insert, btn_settings;
    Intent activity_travel, activity_show, activity_settings;
    Intent transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        activity_travel = new Intent(getApplicationContext(),travel.class);
        activity_show = new Intent(getApplicationContext(),show.class);
        activity_settings = new Intent(getApplicationContext(),settings.class);

        //Transfer travels to activity show
        transfer = new Intent(this, show.class);


        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_settings = (Button) findViewById(R.id.btn_settings);


        btn_insert.setOnClickListener( new View.OnClickListener(){
            public void  onClick (View v){
                startActivity(activity_travel);
            }
        });

        btn_show.setOnClickListener( new View.OnClickListener(){
            public void  onClick (View v){
                myTravels showTravels = new myTravels();
                showTravels.execute();
            }
        });

        btn_settings.setOnClickListener( new View.OnClickListener(){
            public void  onClick (View v){
                startActivity(activity_settings);
            }
        });
    }




    public class myTravels extends AsyncTask<String,String,String> {
        ProgressDialog progressDialog;
        String message = "";
        DataBaseStatements db = new DataBaseStatements();
        ArrayList<String[]> tbl_travel_option = new ArrayList<String[]>();



        @Override
        protected void onPreExecute (){
            progressDialog = new ProgressDialog(options.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Cargando viajes...");
            progressDialog.show();

        }
        @Override
        protected void onPostExecute(String r) {

            if (db.status == "OK")
            {
                //Navigate to Show Activity
                transfer.putExtra("tbl_travel_option", tbl_travel_option);
                startActivity(transfer);
            }
            progressDialog.dismiss();
            //Show Toast Message
            Toast.makeText(options.this, r, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {

            tbl_travel_option = db.getTravels();
            message =  db.message;
            return message;

        }
    }
}
