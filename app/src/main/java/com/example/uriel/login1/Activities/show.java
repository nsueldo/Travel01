package com.example.uriel.login1.Activities;

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

import com.example.uriel.login1.MySQL.DataBaseStatements;
import com.example.uriel.login1.R;


public class show extends AppCompatActivity {
    //Class Declarations
    Button btn_remove;
    ArrayList<String[]> tbl_travel_2;
    travels Travels;
    deleteTravels delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_remove.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                delete = new deleteTravels();
                delete.execute("");
            }
        });
        tbl_travel_2 = (ArrayList<String[]>) getIntent().getSerializableExtra("tbl_travel_option");
        fillTable(tbl_travel_2);

    }

    public void fillTable(ArrayList<String[]> tbl_travel_2){

        //Get Table from Screen
        TableLayout tbl_travels = (TableLayout) findViewById(R.id.travelTable);
        //Check if there are more than one row in the table
        //and delete all rows except the header
        while (tbl_travels.getChildCount() > 1) {
            TableRow row =  (TableRow)tbl_travels.getChildAt(1);
            tbl_travels.removeView(row);
        }

        //Loop each line (row) of the List
        for (int i = 1; i < tbl_travel_2.size(); i++){
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
            String[] item = tbl_travel_2.get(i-1);
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
        for (int i = 1; i < qty_rows; i++){
            TableRow row = (TableRow)tbl_travels.getChildAt(i);
            CheckBox cbx = (CheckBox)row.getChildAt(0);
            if (cbx.isChecked()){
                TextView tv = (TextView)row.getChildAt(1);
                String travel_id = tv.getText().toString();
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
            progressDialog.setMessage("Cargando viajes...");
            progressDialog.show();

        }
        @Override
        protected void onPostExecute(String r) {
            fillTable(tbl_travel_2);
            progressDialog.dismiss();
            //Show Toast Message
            Toast.makeText(show.this, r, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {

            DataBaseStatements db = new DataBaseStatements();
            tbl_travel_2 = new ArrayList<String[]>();
            tbl_travel_2 = db.getTravels();
            message =  db.message;
            return message;

        }
    }

    public class deleteTravels extends AsyncTask<String,String,String>{
        //Create progress dialog
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute (){
            //Show Popup of Deletetion
            progressDialog = new ProgressDialog(show.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Borrando viajes...");
            progressDialog.show();
        }
        @Override
        protected void onPostExecute(String r) {
            progressDialog.dismiss();
            Travels = new travels();
            Travels.execute("");
        }

        @Override
        protected String doInBackground(String... params) {
            deleteSelectedRows();
            return "";
        }
    }
}
