package com.example.uriel.login1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import android.view.Gravity;
import android.widget.Toast;

public class show extends AppCompatActivity {
    Button btn_remove;
    ArrayList<String[]> tbl_travel_2;
    travels Travels;
    boolean delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //progressDialog = new ProgressDialog(show.this,R.style.AppTheme_Dark_Dialog);
        delete = false;
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_remove.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //progressDialog.setMessage("Deleting Travels...");
                delete = true;
                Travels = new travels();
                Travels.execute("");

                //deleteSelectedRows();
                //progressDialog.dismiss();
            }
        });
        Travels = new travels();
        Travels.execute("");
        //fillTable();
    }

    public void fillTable(){

        //Declaration of a List
        //tbl_travel_2 = new ArrayList<String[]>();
        //Get Table from Screen
        TableLayout tbl_travels = (TableLayout) findViewById(R.id.travelTable);
        tbl_travels.removeAllViews();
        //Get Travels from Database into the List
        //DataBaseStatements db = new DataBaseStatements();
        //tbl_travel_2 = db.getTravels();
        //Loop each line (row) of the List
        for (int i = 0; i < tbl_travel_2.size(); i++){
            //Create a new object row
            TableRow row= new TableRow(this);
            //Set Layout of the row
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(lp);
            //For each line, there are a CheckBox
            CheckBox chb = new CheckBox(this);
            chb.setGravity(Gravity.CENTER_HORIZONTAL);
            chb.setBackgroundResource(R.drawable.cell_shape);
            row.addView(chb);
            String[] item = tbl_travel_2.get(i);
            //Loop each cell of the current line
            for (int j = 0; j < tbl_travel_2.get(i).length; j++){
                //Each Cell is a Text View
                TextView tv = new TextView(this);
                //Get current Cell and add this to the row
                tv.setText(item[j]);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setBackgroundResource(R.drawable.cell_shape);
                row.addView(tv);
            }
            //Add the row to the Screen Table
            tbl_travels.addView(row,i);
        }
    }

    public void deleteSelectedRows(){
        DataBaseStatements db = new DataBaseStatements();
        TableLayout tbl_travels = (TableLayout) findViewById(R.id.travelTable);
        int qty_rows = tbl_travels.getChildCount();
        for (int i = 0; i < qty_rows; i++){
            TableRow row = (TableRow)tbl_travels.getChildAt(i);
            CheckBox cbx = (CheckBox)row.getChildAt(0);
            if (cbx.isChecked()){
                TextView tv = (TextView)row.getChildAt(1);
                String travel_id = tv.getText().toString();
                //DataBaseStatements db = new DataBaseStatements();
                db.deleteTravel(travel_id);
            }
        }
    }

    public class travels extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        String message = "";
        @Override
        protected void onPreExecute (){
            progressDialog = new ProgressDialog(show.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Loading Travels...");
            progressDialog.show();

        }
        @Override
        protected void onPostExecute(String r) {
            /*
            if (delete){
                deleteSelectedRows();
                delete = false;
            }*/
            fillTable();
            progressDialog.dismiss();
            //Show Toast Message
            Toast.makeText(show.this, r, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            if (delete){
                deleteSelectedRows();
            }

            DataBaseStatements db = new DataBaseStatements();
            tbl_travel_2 = new ArrayList<String[]>();
            tbl_travel_2 = db.getTravels();
            message =  db.message;

            return message;

        }
    }
}
