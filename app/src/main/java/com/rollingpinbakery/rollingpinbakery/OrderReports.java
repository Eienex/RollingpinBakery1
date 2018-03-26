package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Order;

import java.util.ArrayList;

public class OrderReports extends AppCompatActivity {

    ListView listView;
    ArrayList<Order> orders;
    private static OrderReportAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reports_order);
    }
    @Override
    protected void onResume(){
        super.onResume();
        listView = findViewById(R.id.orders_list);
        //orders = (ArrayList<Order>)AppDatabase.getAppDatabase(this).orderDao().getOrderReports();
        //adapter2 = new Order_ReportAdapter(this, orders);
        //listView.setAdapter(adapter2);
    }
}
