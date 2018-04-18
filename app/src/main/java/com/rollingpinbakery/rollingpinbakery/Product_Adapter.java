package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;
import com.rollingpinbakery.rollingpinbakery.Data.Product;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;

import java.util.ArrayList;

/**
 * Created by rudst on 2/28/2018.
 */

public class Product_Adapter extends ArrayAdapter<Product> implements View.OnClickListener{

    private ArrayList<Product> dataSet;
    Context context;


        private static class ViewHolder{
        TextView name;
        TextView price;
        TextView salePrice;
        TextView productType;
        TextView productDesc;
        TextView prodIsFeatured;
        }

    public Product_Adapter(Context context, ArrayList<Product> products){
        super(context, R.layout.row_item, products);
        this.dataSet = products;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Product product = getItem(postition);
        Product_Adapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new Product_Adapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.price = convertView.findViewById(R.id.price);
            viewHolder.salePrice = convertView.findViewById(R.id.salePrice);
            viewHolder.productType = convertView.findViewById(R.id.productType);
            viewHolder.productDesc = convertView.findViewById(R.id.productDesc);
            viewHolder.prodIsFeatured = convertView.findViewById(R.id.prodIsFeatured);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Product_Adapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.name.setText("Name: " + product.getProdName());
        viewHolder.price.setText(("Price: " + product.getProdRetailPrice()));

        if(product.getProdSalePrice() != null) {
            viewHolder.salePrice.setText("Sale Price: " + product.getProdSalePrice());
        }
        else{
            viewHolder.salePrice.setText("Sale Price: None");
        }
        viewHolder.productType.setText("Category: " + product.getProdType());
        viewHolder.productDesc.setText("Description: " + product.getProdDesc());
        if(product.getProdFeatured() == 1){
            viewHolder.prodIsFeatured.setText("Featured Product!");
            viewHolder.prodIsFeatured.setTextColor(Color.RED);
        }else {
            viewHolder.prodIsFeatured.setText("");
        }

        Button editBtn = convertView.findViewById(R.id.EditBtn);
        Button deleteButton = convertView.findViewById(R.id.DeleteBtn);

        final String id = product.get_prodId();

/*
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtName = name.getText().toString();
                final String txtPrice = price.getText().toString();
                final String txtSalePrice = salePrice.getText().toString();
                final String txtType = productType.getText().toString();
                final String txtDesc = productDesc.getText().toString();
                final String txtProdIsFeatured = prodIsFeatured.getText().toString();
                final String txtProdImg = prodIsFeatured.getText().toString();
                editProduct(view, id, txtName, txtPrice, txtSalePrice, txtType, txtDesc, txtProdIsFeatured);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete the item from the database
                update(product);
                notifyDataSetChanged();
                view.getContext().startActivity(new Intent(getContext(), Admin_Products.class));
            }
        });
        */
        return convertView;
    }

    public void reset(Product product){
        this.remove(product);
        notifyDataSetChanged();
    }

    public void update(Product product){
        try{
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
            databaseAccess.open();
            databaseAccess.deleteProduct(product);
            databaseAccess.close();
        }catch(Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        //AppDatabase.getAppDatabase(getContext()).productDao().delete(product);
    }

    public void editProduct(View view, String id, String name, String price, String salePrice, String type, String desc, String prodIsFeatured){
        Intent formResult = new Intent(getContext(), AdminProductEdit.class);

        formResult.putExtra("txt_productID", id);
        formResult.putExtra("txt_productName", name);
        formResult.putExtra("txt_productPrice",price);
        formResult.putExtra("txt_productSalePrice", salePrice);
        formResult.putExtra("txt_productType", type);
        formResult.putExtra("txt_productDesc", desc);
        formResult.putExtra("txt_prodIsFeatured", prodIsFeatured);

        view.getContext().startActivity(formResult);
    }

    public void viewProduct(View view, String id, String name, String price, String salePrice, String type, String desc, String prodIsFeatured){
        Intent formResult = new Intent(getContext(), AdminProductEdit.class);

        formResult.putExtra("txt_productID", id);
        formResult.putExtra("txt_productName", name);
        formResult.putExtra("txt_productPrice",price);
        formResult.putExtra("txt_productSalePrice", salePrice);
        formResult.putExtra("txt_productType", type);
        formResult.putExtra("txt_productDesc", desc);
        formResult.putExtra("txt_prodIsFeatured", prodIsFeatured);

        view.getContext().startActivity(formResult);
    }
    @Override
    public void onClick(View v) {
    }
}
