package com.example.uriel.login1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.app.ProgressDialog;

public class register extends AppCompatActivity {
    List<String> companies_list;
    ArrayList<String[]> tbl_companies;
    String company_id;
    Spinner s_companies;
    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Set Company Spinner values from DataBase
        PrepareRegister prepareRegister = new PrepareRegister();
        prepareRegister.execute("");
    }

    protected void setCompanySpinner(){
        //Declaration of Variables
        DataBaseStatements db;
        String[] row;

        //Begin of Flow Logic
        s_companies = (Spinner) findViewById(R.id.s_companies);
        db = new DataBaseStatements();
        tbl_companies = db.getCompanies();
        companies_list = new ArrayList<String>();

        for (int i = 0; i < tbl_companies.size(); i++){
            row = tbl_companies.get(i);
            companies_list.add(row[1]);
        }
    }

    public class CustomOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            String[] row = tbl_companies.get(pos);
            company_id   = row[0];
            Log.w("URIEL",company_id);
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }

    public class PrepareRegister extends AsyncTask<String,String,String> {
        //Declaration
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            //Start Progress Bar
            progressDialog = new ProgressDialog(register.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Preparing...");
            progressDialog.show();
        }

        //Show Toast with the Return Message
        @Override
        protected void onPostExecute(String r) {
            //Dismiss Progress Bar
            dataAdapter = new ArrayAdapter<String>(register.this, R.layout.spinner, companies_list);
            s_companies.setAdapter(dataAdapter);
            s_companies.setOnItemSelectedListener(new CustomOnItemSelectedListener());
            progressDialog.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            setCompanySpinner();
            return "";
        }
    }
}
