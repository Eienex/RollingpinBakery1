package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customer>
implements View.OnClickListener {

    private ArrayList<Customer> dataset;
    Context mContext;


    private static class ViewHolder {
        TextView name;
        TextView userName;
        TextView custEmail;
        TextView custPassword;
        TextView custRole;
    }

    public CustomerAdapter(ArrayList<Customer> data, Context context) {
        super(context, R.layout.row_item_customers, data);
        this.dataset = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer customer = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_customers, parent, false);
            viewHolder.name = convertView.findViewById(R.id.Name);
            viewHolder.userName = convertView.findViewById(R.id.userName);
            viewHolder.custEmail = convertView.findViewById(R.id.custEmail);
            viewHolder.custPassword = convertView.findViewById(R.id.custPassword);
            viewHolder.custRole = convertView.findViewById(R.id.custRole);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.name.setText("Name: " + customer.getCustFName() + " " + customer.getCustLName());
        viewHolder.userName.setText("User Name: " + customer.getCustUsername());
        viewHolder.custEmail.setText("Email: " + customer.getCustEmail());
        viewHolder.custPassword.setText("Password: " + customer.getCustPassword());
        viewHolder.custRole.setText("Customer Role: " + customer.getCustType());

        Button editBtn = convertView.findViewById(R.id.EditBtn);
        Button deleteButton = convertView.findViewById(R.id.DeleteBtn);

    /*    editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtFName = customer.getCustFName();
                final String txtLName = customer.getCustLName();
                final String txtUsername = username.getText().toString();
                final String txtPassword = password.getText().toString();
                final String txtEmail = email.getText().toString();
                final String txtRole = custRole.getText().toString();
                // editCustomer(view, id, txtFName, txtLName, txtUsername, txtPassword, txtEmail, txtRole);
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
        }); */

        return convertView;
    }
    @Override
    public void onClick(View v) {
    }
}
/*
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
*/
