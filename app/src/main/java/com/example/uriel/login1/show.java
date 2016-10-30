package com.example.uriel.login1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Uriel on 29/10/2016.
 */

public class show extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();
    }

    public void init(){
        ArrayList<String[]> tbl_travel_2 = new ArrayList<String[]>();

/*        tbl_travel_2.add(new String[]{"Linea 1", "Linea 1 - Col 2", "Linea 1 - Col 3"});
        tbl_travel_2.add(new String[]{"Linea 2", "Linea 2 - Col 2", "Linea 2 - Col 3"});
        tbl_travel_2.add(new String[]{"Linea 3", "Linea 3 - Col 2", "Linea 3 - Col 3"});*/
        TableLayout tbl_travels = (TableLayout) findViewById(R.id.travelTable);
        DataBaseStatements db = new DataBaseStatements();
        tbl_travel_2 = db.getTravels();
        /*for (int i = 0; i <2; i++) {
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setPadding(10,10,10,10);
            row.setLayoutParams(lp);
            CheckBox checkBox = new CheckBox(this);
            TextView tv = new TextView(this);
            tv.setPadding(10,10,10,10);
            tv.setBackgroundColor(Color.GREEN);
            checkBox.setText("hello");
            tv.setText("10");
            row.addView(checkBox);
            row.addView(tv);
            tbl_travels.addView(row,i);
        }*/

        for (int i = 0; i < tbl_travel_2.size(); i++){
                TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setPadding(10,10,10,10);
            row.setLayoutParams(lp);

            for (int j = 0; j < tbl_travel_2.get(i).length; j++){
                TextView tv = new TextView(this);
                tv.setPadding(10,10,10,10);
                tv.setBackgroundColor(Color.GREEN);
                String[] item = tbl_travel_2.get(i);
                tv.setText(item[j]);
                row.addView(tv);
            }
            tbl_travels.addView(row,i);
        }
    }
}
