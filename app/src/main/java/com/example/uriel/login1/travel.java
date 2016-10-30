package com.example.uriel.login1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class travel extends AppCompatActivity {
    //Declare UI Elements of Travel Screen
    EditText et_source, et_target;
    Button btn_go, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        //Instance UI Elements of Travel Screen
        et_source = (EditText) findViewById(R.id.et_source);
        et_target = (EditText) findViewById(R.id.et_target);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_exit = (Button) findViewById(R.id.btn_exit);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travel.DoLogin doLogin = new travel.DoLogin();
                doLogin.execute("");
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call root activity to finish the application
                Intent intent = new Intent(getApplicationContext(),login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
    }

    public class DoLogin extends AsyncTask<String, String, String> {
        //Get information from the screen
        String source = et_source.getText().toString();
        String target = et_target.getText().toString();

        @Override
        protected void onPostExecute(String r) {
            //Show Toast Message
            Toast.makeText(travel.this, r, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            //Insert a new Travel and return message
            DataBaseStatements db = new DataBaseStatements();
            db.insertTravel(source, target);
            return db.message;
        }
    }
}