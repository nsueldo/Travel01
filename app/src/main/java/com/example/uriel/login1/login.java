package com.example.uriel.login1;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;


public class login extends AppCompatActivity {

    //Declaration of UI Elements
    EditText et_user, et_password;
    Button   btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        //Get UI Elements from Login Activity
        et_user = (EditText)findViewById(R.id.et_user);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        //Set OnClick action for BTN_LOGIN
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin  doLogin = new DoLogin();
                doLogin.execute("");
            }
        });
    }

    public class DoLogin extends AsyncTask<String,String,String>{
        //Declaration
        ProgressDialog progressDialog;
        String userid   = et_user.getText().toString();
        String password = et_password.getText().toString();
        Intent activity_options = new Intent(getApplicationContext(), options.class);

        @Override
        protected void onPreExecute() {
            //Start Progress Bar
            progressDialog = new ProgressDialog(login.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
        }

        //Show Toast with the Return Message
        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(login.this,r,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            //Check the User and Password from Login Activity
            DataBaseStatements db = new DataBaseStatements();
            db.checkUser(userid, password);
                if (db.status == "OK")
                {
                    //Navigate to Options Activity
                    startActivity(activity_options);
                }
            //Dismiss Progress Bar
            progressDialog.dismiss();
            //Return Login Message
            return db.message;
        }
    }
}
