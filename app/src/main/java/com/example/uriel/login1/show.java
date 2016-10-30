package com.example.uriel.login1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import android.support.v4.content.res.ResourcesCompat;

public class show extends AppCompatActivity {
    Button btn_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_remove.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                deleteSelectedRows();
            }
        });

        fillTable();
    }

    public void fillTable(){
        //Declaration of a List
        ArrayList<String[]> tbl_travel_2 = new ArrayList<String[]>();
        //Get Table from Screen
        TableLayout tbl_travels = (TableLayout) findViewById(R.id.travelTable);
        tbl_travels.removeAllViews();
        //Get Travels from Database into the List
        DataBaseStatements db = new DataBaseStatements();
        tbl_travel_2 = db.getTravels();
        //Loop each line (row) of the List
        for (int i = 0; i < tbl_travel_2.size(); i++){
            //Create a new object row
            TableRow row= new TableRow(this);
            //Set Layout of the row
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            //For each line, there are a CheckBox
            CheckBox chb = new CheckBox(this);
            row.addView(chb);
            String[] item = tbl_travel_2.get(i);
            //Loop each cell of the current line
            for (int j = 0; j < tbl_travel_2.get(i).length; j++){
                //Each Cell is a Text View
                TextView tv = new TextView(this);
                //Get current Cell and add this to the row
                tv.setText(item[j]);
                tv.setBackgroundResource(R.drawable.cell_shape);
                row.addView(tv);
            }
            //Add the row to the Screen Table
            tbl_travels.addView(row,i);
        }
    }

    public void deleteSelectedRows(){
        TableLayout tbl_travels = (TableLayout) findViewById(R.id.travelTable);
        int qty_rows = tbl_travels.getChildCount();
        for (int i = 0; i < qty_rows; i++){
            TableRow row = (TableRow)tbl_travels.getChildAt(i);
            CheckBox cbx = (CheckBox)row.getChildAt(0);
            if (cbx.isChecked()){
                TextView tv = (TextView)row.getChildAt(1);
                String travel_id = tv.getText().toString();
                tv.setBackgroundColor(Color.GREEN);
                DataBaseStatements db = new DataBaseStatements();
                db.deleteTravel(travel_id);
            }
        }

        this.fillTable();
    }
}
