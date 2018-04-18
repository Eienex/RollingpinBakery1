package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.Product;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;

import java.util.ArrayList;

/**
 * Created by equno_000 on 3/26/2018.
 */

public class ItemAdapter extends ArrayAdapter<Product> {
    private ArrayList<com.rollingpinbakery.rollingpinbakery.Data.Product> dataSet;
    Context context;

    public ItemAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.content_item, products);
        this.dataSet = products;
        this.context = context;
    }

    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent) {
        final Product product = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.content_item, parent, false);

        final TextView productName = convertView.findViewById(R.id.name);
        final ImageView productImage = convertView.findViewById(R.id.prodImg);
        final TextView productPrice = convertView.findViewById(R.id.price);
        final TextView productSalePrice = convertView.findViewById(R.id.salePrice);
        final TextView productType = convertView.findViewById(R.id.productType);
        final TextView productDesc = convertView.findViewById(R.id.productDesc);
        Button buyBtn = convertView.findViewById(R.id.BuyBtn);

        final String id = product.get_prodId();

        productName.setText(product.getProdName());
        productImage.setImageDrawable(Drawable.createFromPath(product.getProdImage()));
        productDesc.setText(product.getProdDesc());
        productSalePrice.setText(product.getProdRetailPrice().toString());
        productType.setText(product.getProdType());

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtName = productName.getText().toString();
                final String txtPrice = productPrice.getText().toString();
                final String txtSalePrice = productSalePrice.getText().toString();
                final String txtType = productType.getText().toString();
                final String txtDesc = productDesc.getText().toString();
                cart(view, id, txtName, txtPrice, txtSalePrice, txtType, txtDesc);
            }
        });

        return convertView;
    }

    public void cart(View view, String id, String name, String price, String salePrice, String type, String desc){
            Intent formResult = new Intent(getContext(), Item.class);

            formResult.putExtra("txt_productID", id);
            formResult.putExtra("txt_productName", name);
            formResult.putExtra("txt_productPrice",price);
            formResult.putExtra("txt_productSalePrice", salePrice);
            formResult.putExtra("txt_productType", type);
            formResult.putExtra("txt_productDesc", desc);

            view.getContext().startActivity(formResult);
        }

}
