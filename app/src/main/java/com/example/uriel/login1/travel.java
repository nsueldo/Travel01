package com.example.uriel.login1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//MySQL Connection
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//Date and time
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class travel extends AppCompatActivity {

    ConnectionClass connectionClass;
    EditText et_source, et_target;
    Button btn_go;

    Date datenow = new Date();
    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
    String mydate = formatdate.format(datenow);

    Date timenow = new Date();
    SimpleDateFormat formattime = new SimpleDateFormat("hh:mm:ss");
    String mytime = formattime.format(timenow);



 //   String a = "2012-12-31";
 //   String b = "101112";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        connectionClass = new ConnectionClass();
        et_source = (EditText) findViewById(R.id.et_source);
        et_target = (EditText) findViewById(R.id.et_target);
        btn_go = (Button) findViewById(R.id.btn_go);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travel.DoLogin doLogin = new travel.DoLogin();
                doLogin.execute("");
            }
        });

    }


    public class DoLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;


        String source = et_source.getText().toString();
        String target = et_target.getText().toString();
//        Intent new_activity = new Intent(getApplicationContext(), travel.class);


        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(travel.this, r, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            if (source.trim().equals("") || target.trim().equals(""))
                z = "Please enter Source and Target";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        //String query = "SELECT * FROM travels";
                        String query = "INSERT INTO travels (travel_source, travel_target, travel_date, travel_time) VALUES('"+source+"','"+target+"','"+mydate+"', '"+mytime+"');";
                        Statement stmt = con.createStatement(); //genera consulta
                        ResultSet rs2 = stmt.executeQuery(query);

                        if (rs2.next()){
                            z = "Success !";
                        }else{
                            z=" Error !";
                        }

/**                        if (rs.next()) {
                            String locked = "";
                            //locked = null;
                            locked = rs.getString("locked");
                            if (locked == null) {
                                z = "Login successfull";
                                startActivity(new_activity);
                            } else {
                                z = "User locked";

                            }

                            isSuccess = true;
                        } else {
                            z = "User or Pass wrong";
                            isSuccess = false;
                        }*/
                        con.close();
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    System.out.print(ex.getMessage());
                    z = "Exceptions";
                }
            }
            return z;
        }
    }
}