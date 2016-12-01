package com.example.uriel.login1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.app.ProgressDialog;
import android.widget.Toast;

public class register extends AppCompatActivity {
    List<String> companies_list;
    ArrayList<String[]> tbl_companies;
    String company_id;
    Spinner s_companies;
    ArrayAdapter<String> dataAdapter;

    EditText et_rname, et_rsurname, et_remail, et_ruser, et_rpass;
    String name, surname, email, user, pass, status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Set Company Spinner values from DataBase
        PrepareRegister prepareRegister = new PrepareRegister();
        prepareRegister.execute("");

        et_rname = (EditText) findViewById(R.id.et_rname);
        et_rsurname = (EditText) findViewById(R.id.et_rsurname);
        et_remail = (EditText) findViewById(R.id.et_remail);
        et_ruser = (EditText) findViewById(R.id.et_ruser);
        et_rpass = (EditText) findViewById(R.id.et_rpassword);
    }

    public void registerUser(View view){
        Register reg = new Register();
        reg.execute("");

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

        //Show Toast with the Return Message
        @Override
        protected void onPostExecute(String r) {
            //Dismiss Progress Bar
            dataAdapter = new ArrayAdapter<String>(register.this, R.layout.spinner, companies_list);
            s_companies.setAdapter(dataAdapter);
            s_companies.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        }

        @Override
        protected String doInBackground(String... params) {
            setCompanySpinner();
            return "";
        }
    }

    public class Register extends AsyncTask<String,String,String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            progressDialog = new ProgressDialog(register.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Register User...");
            progressDialog.show();

            name = et_rname.getText().toString();
            surname = et_rsurname.getText().toString();
            email = et_remail.getText().toString();
            user = et_ruser.getText().toString();
            pass = et_rpass.getText().toString();
        }
        @Override
        protected void onPostExecute(String r) {

            //Dismiss Progress Bar
            progressDialog.dismiss();
            Toast.makeText(register.this, r, Toast.LENGTH_SHORT).show();

            if ( status == "OK"){
                finish();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            DataBaseStatements db = new DataBaseStatements();
            db.insertUser(name, surname, email, user, pass, company_id);
            status = db.status;
            return db.message;
        }
    }
}
