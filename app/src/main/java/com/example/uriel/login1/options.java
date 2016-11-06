package com.example.uriel.login1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Uriel on 29/10/2016.
 */

public class options extends AppCompatActivity {
    Button btn_show, btn_insert, btn_register;
    Intent activity_travel, activity_show, activity_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        activity_travel = new Intent(getApplicationContext(),travel.class);
        activity_show = new Intent(getApplicationContext(),show.class);
        activity_register = new Intent(getApplicationContext(), register.class);

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_register =(Button) findViewById(R.id.btn_register);

        btn_insert.setOnClickListener( new View.OnClickListener(){
            public void  onClick (View v){
                startActivity(activity_travel);
            }
        });

        btn_show.setOnClickListener( new View.OnClickListener(){
            public void  onClick (View v){
                startActivity(activity_show);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener(){
            public  void onClick( View v){
                startActivity(activity_register);
            }
        });
    }

}
