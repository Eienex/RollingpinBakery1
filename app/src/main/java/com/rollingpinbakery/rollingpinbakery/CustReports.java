package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.util.ArrayList;

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
        listView = findViewById(R.id.list_item);
        customers = (ArrayList<Customer>) AppDatabase.getAppDatabase(this).customerDao().getCustReports();
        adapter = new Cust_ReportAdapter(this, customers);
        listView.setAdapter(adapter);
    }

}
