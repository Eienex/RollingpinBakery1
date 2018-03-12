package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

import java.util.ArrayList;

public class InventReports extends AppCompatActivity {
    ListView listView;
    ArrayList<Product> products;
    private static Invent_ReportAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reports_invent);

    }

    @Override
    protected void onResume(){
        super.onResume();
        listView = findViewById(R.id.product_list);
        products = (ArrayList<Product>) AppDatabase.getAppDatabase(this).productDao().getInventReports();
        adapter2 = new Invent_ReportAdapter(this, products);
        listView.setAdapter((ListAdapter) adapter2);
    }

}
