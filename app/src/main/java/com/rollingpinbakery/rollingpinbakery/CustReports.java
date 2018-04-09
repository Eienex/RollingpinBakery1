package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

public class CustReports extends AppCompatActivity {

    ListView listView;
    ArrayList<Customer> customers;
    private static Cust_ReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reports_customer);

    }

    @Override
    protected void onResume(){
        super.onResume();
        try{
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            listView = findViewById(R.id.list_item);
            //customers = (ArrayList<Customer>) AppDatabase.getAppDatabase(this).customerDao().getCustReports();
            customers = (ArrayList<Customer>) databaseAccess.getAllCustomers();
            databaseAccess.close();
            adapter = new Cust_ReportAdapter(this, customers);
            listView.setAdapter(adapter);
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
