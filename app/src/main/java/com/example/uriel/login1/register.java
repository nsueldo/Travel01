package com.example.uriel.login1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class register extends AppCompatActivity {
    List<String> companies_list;
    ArrayList<String[]> tbl_companies;
    DataBaseStatements db;
    Spinner s_companies;
    ArrayAdapter<String> dataAdapter;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        s_companies = (Spinner) findViewById(R.id.s_companies);
        db = new DataBaseStatements();
        tbl_companies = db.getCompanies();
        companies_list = new ArrayList<String>();

        for (i = 0; i < tbl_companies.size(); i++){
            String[] row = tbl_companies.get(i);
            String item = row[1];
            companies_list.add(item);
        }


        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, companies_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_companies.setAdapter(dataAdapter);
    }


}
