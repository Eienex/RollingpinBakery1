package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustAdapter  extends ArrayAdapter<Customer>
implements View.OnClickListener{

    private ArrayList<Customer> dataset;
    Context mContext;

    private static class ViewHolder{
        TextView name;
        TextView userName;
        TextView custEmail;
        TextView custPassword;
        TextView custRole;
    }

    public CustAdapter(ArrayList<Customer> data, Context context){
        super(context, R.layout.row_item_customers, data);
        this.dataset = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Customer customer = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null){
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
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
            result = convertView;
        }

        viewHolder.name.setText("Name: " + customer.getCustFName() + " " + customer.getCustLName());
        viewHolder.userName.setText("User Name: " + customer.getCustUsername());
        viewHolder.custEmail.setText("Email: " + customer.getCustEmail());
        viewHolder.custPassword.setText("Password: " + customer.getCustPassword());
        viewHolder.custRole.setText("Customer Role: " + customer.getCustType());

        return convertView;
    }

    @Override
    public void onClick(View v){

    }
}
