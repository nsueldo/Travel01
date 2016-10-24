package com.example.uriel.login1;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class login extends AppCompatActivity {

    ConnectionClass connectionClass;
    EditText et_user, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        connectionClass = new ConnectionClass();
        et_user = (EditText)findViewById(R.id.et_user);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin  doLogin = new DoLogin();
                doLogin.execute("");
            }
        });

    }

/**Codigo para pasar de activity
   public void change(View v){
        Intent new_activity = new Intent(this, travel.class);
        startActivity(new_activity);
   } */
    public class DoLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;

        String userid = et_user.getText().toString();
        String password = et_password.getText().toString();
        Intent new_activity = new Intent(getApplicationContext(), travel.class);



        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(login.this,r,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Please enter User Id and Password";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {

                        String query = "select * from users where user_id='" + userid + "' and user_pass='" + password + "'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            String locked = "";
                            locked = null;
                            locked = rs.getString("locked");
                            if (locked == null){
                                z = "Login successfull";
                                startActivity(new_activity);
                            }else{
                                z = "User locked";

                            }

                            isSuccess=true;
                        }
                        else
                        {
                            z = "User or Pass wrong";
                            isSuccess = false;
                        }
                        con.close();
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;
        }

    }
}
