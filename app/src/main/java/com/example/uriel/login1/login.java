package com.example.uriel.login1;

import android.content.SharedPreferences;
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
    Button   btn_login,btn_register;
    Intent activity_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Entrar al Sistema");

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        String[] data;
        data = sharedPreferences.getLogin(getApplicationContext());
        if (data[0] != null){
            Intent activity_options = new Intent(getApplicationContext(), options.class);
            //Navigate to Options Activity
            startActivity(activity_options);
        }

        //Get UI Elements from Login Activity
        et_user = (EditText)findViewById(R.id.et_user);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register =(Button) findViewById(R.id.btn_register);

        //Change to Activity Register
        activity_register = new Intent(getApplicationContext(), register.class);

        //Set OnClick action for BTN_LOGIN
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin  doLogin = new DoLogin();
                doLogin.execute("");
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener(){
            public  void onClick( View v){
                startActivity(activity_register);
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
            progressDialog.setMessage("Validando ingreso...");
            progressDialog.setCancelable(false);
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
                    //Save Login Data in the App
                    sharedPreferences.saveLogin(getApplicationContext(), userid, password);
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
