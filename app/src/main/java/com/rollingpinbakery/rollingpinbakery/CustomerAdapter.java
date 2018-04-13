package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;


import java.util.ArrayList;

/**
 * Created by rudst on 2/28/2018.
 */

public class CustomerAdapter extends ArrayAdapter<Customer> {
    private ArrayList<Customer> dataSet;
    Context context;

    public CustomerAdapter(Context context, ArrayList<Customer> customers){
        super(context, R.layout.row_item_customers, customers);
        this.dataSet = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Customer customer = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_item_customers, parent, false);

        final TextView Name = convertView.findViewById(R.id.Name);
        final TextView username = convertView.findViewById(R.id.userName);
        final TextView password = convertView.findViewById(R.id.custPassword);
        final TextView email = convertView.findViewById(R.id.custEmail);
        final TextView custRole = convertView.findViewById(R.id.custRole);
        Button editBtn = convertView.findViewById(R.id.EditBtn);
        Button deleteButton = convertView.findViewById(R.id.DeleteBtn);

        final String id = customer.get_custId();

        Name.setText("Name: " + customer.getCustFName() + " " + customer.getCustLName());
        username.setText("Username: " + customer.getCustUsername());
        password.setText("Password: " + customer.getCustPassword());
        email.setText("Email: " + customer.getCustEmail());
        custRole.setText("Role: " + customer.getCustType());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtFName = customer.getCustFName();
                final String txtLName = customer.getCustLName();
                final String txtUsername = username.getText().toString();
                final String txtPassword = password.getText().toString();
                final String txtEmail = email.getText().toString();
                final String txtRole = custRole.getText().toString();
                editCustomer(view, id, txtFName, txtLName, txtUsername, txtPassword, txtEmail, txtRole);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete the item from the database
                update(customer);
                notifyDataSetChanged();
                view.getContext().startActivity(new Intent(getContext(), AdminCustomers.class));
            }
        });

        return convertView;
    }

    public void reset(Customer customer){
        this.remove(customer);
        notifyDataSetChanged();
    }

    public void update(Customer customer){
        //AppDatabase.getAppDatabase(getContext()).customerDao().delete(customer);
        try{
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
            databaseAccess.open();
            //AppDatabase.getAppDatabase(getContext()).customerDao().delete(customer);
            databaseAccess.deleteCustomer(customer);
            databaseAccess.close();
        }catch(Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void Delete(Customer customer){
        try{
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
            databaseAccess.open();
            //AppDatabase.getAppDatabase(getContext()).customerDao().delete(customer);
            databaseAccess.deleteCustomer(customer);
            databaseAccess.close();
        }catch(Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void editCustomer(View view, String id, String fName, String lName, String username, String password, String email, String role){
        Intent formResult = new Intent(getContext(), AdminCustomerEdit.class);

        formResult.putExtra("txt_custID", id);
        formResult.putExtra("txt_custFName", fName);
        formResult.putExtra("txt_custLName",lName);
        formResult.putExtra("txt_username", username);
        formResult.putExtra("txt_password", password);
        formResult.putExtra("txt_email", email);
        formResult.putExtra("txt_role", role);

        view.getContext().startActivity(formResult);
    }

}
