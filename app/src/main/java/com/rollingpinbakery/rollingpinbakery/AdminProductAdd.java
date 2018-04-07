package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

public class AdminProductAdd extends AppCompatActivity {

    String[] productTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_add);

        productTypes = getResources().getStringArray(R.array.productTypes);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, productTypes);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void AddProduct(View view){
        EditText prodName = (EditText)findViewById(R.id.ProductName);
        EditText prodPrice = (EditText)findViewById(R.id.ProductPrice);
        EditText prodSalesPrice = (EditText)findViewById(R.id.ProductSalePrice);
        EditText prodDesc = (EditText)findViewById(R.id.ProductDescription);
        EditText prodImage = (EditText)findViewById(R.id.ProductImage);

        String txtName = prodName.getText().toString();
        String txtPrice = prodPrice.getText().toString();
        String txtSalePrice = prodSalesPrice.getText().toString();
        String txtDesc = prodDesc.getText().toString();
        String txtImage = prodImage.getText().toString();

        if (txtName.matches("") || txtPrice.matches("")){
            Toast.makeText(getApplicationContext(), "Please fill out the form", Toast.LENGTH_SHORT);
        }
        else {
            double productSalesPrice;
            double productPrice = Double.parseDouble(txtPrice);
            if (txtSalePrice.matches("")){
                productSalesPrice = 0.00;
            }else {productSalesPrice = Double.parseDouble(txtSalePrice);}

            Spinner spinner = findViewById(R.id.spinner);
            String spinnerResult = spinner.getSelectedItem().toString();
            int isFeatured = 0;
            CheckBox isFeaturedProd = findViewById(R.id.IsFeaturedCkbx);
            if (isFeaturedProd.isChecked()){
                isFeatured = 1;
            }
            else {
                isFeatured = 0;
            }


            if (txtSalePrice.matches("")){
                //AppDatabase.getAppDatabase(this).productDao().insert(new Product(txtName, productPrice, 0.00, txtDesc, spinnerResult, isFeatured,null));
                try{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
                    databaseAccess.open();
                    databaseAccess.insertProduct(new Product(txtName, productPrice, 0.00, txtDesc, spinnerResult, isFeatured,null));
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();
            }
            else {
                //AppDatabase.getAppDatabase(this).productDao().insert(new Product(txtName, productPrice, productSalesPrice, txtDesc, spinnerResult, isFeatured,null));
                try{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
                    databaseAccess.open();
                    databaseAccess.insertProduct(new Product(txtName, productPrice, productSalesPrice, txtDesc, spinnerResult, isFeatured,null));
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();
            }
        }

    }

}
