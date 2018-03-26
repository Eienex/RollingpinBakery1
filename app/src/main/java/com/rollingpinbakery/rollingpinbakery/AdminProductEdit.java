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
import com.rollingpinbakery.rollingpinbakery.Data.Product;

public class AdminProductEdit extends AppCompatActivity {

    int id;
    EditText prodName, prodPrice, prodSalePrice, prodDesc, prodImage;
    Spinner productCategory;
    CheckBox prodIsFeatured;
    String[] productTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_edit);

        prodName = (EditText)findViewById(R.id.ProductName);
        prodPrice = (EditText)findViewById(R.id.ProductPrice);
        prodSalePrice = (EditText)findViewById(R.id.ProductSalePrice);
        prodDesc = (EditText)findViewById(R.id.ProductDescription);
        productCategory = (Spinner)findViewById(R.id.spinner);
        prodIsFeatured = (CheckBox)findViewById(R.id.IsFeaturedCkbx);
        prodImage = (EditText)findViewById(R.id.ProductImage);

        id = getIntent().getIntExtra("int_productID", 0);
        String txtName = getIntent().getStringExtra("txt_productName");
        String txtPrice = getIntent().getStringExtra("txt_productPrice");
        String txtSalesPrice = getIntent().getStringExtra("txt_productSalePrice");
        String txtProdDesc = getIntent().getStringExtra("txt_productDesc");
        String txtProdType = getIntent().getStringExtra("txt_productType");
        String txtProdIsFeatured = getIntent().getStringExtra("txt_prodIsFeatured");
        String txtProdImg = getIntent().getStringExtra("txt_productImg");

        //crop the strings
        txtName = txtName.replace("Name: ", "");
        txtPrice = txtPrice.replace("Price: ", "");
        txtSalesPrice = txtSalesPrice.replace("Sale Price: ", "");
        txtProdType = txtProdType.replace("Category: ", "");
        txtProdDesc = txtProdDesc.replace("Description: " , "");
        txtProdImg = txtProdImg.replace("Image: " , "");

        //Set the text for the edittext
        prodName.setText(txtName);
        prodPrice.setText(txtPrice);
        prodSalePrice.setText(txtSalesPrice);
        prodDesc.setText(txtProdDesc);
        prodImage.setText(txtProdImg);

        if (txtProdIsFeatured.matches("")){
            prodIsFeatured.setChecked(false);
        }else{prodIsFeatured.setChecked(true);}

        productTypes = getResources().getStringArray(R.array.productTypes);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, productTypes);
        productCategory.setAdapter(arrayAdapter);
        if (!txtProdType.equals(null)){
            int spinnerPosition = arrayAdapter.getPosition(txtProdType);
            productCategory.setSelection(spinnerPosition);
        }
        productCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void UpdateProduct(View view){
        EditText prodName = (EditText)findViewById(R.id.ProductName);
        EditText prodPrice = (EditText)findViewById(R.id.ProductPrice);
        EditText prodSalesPrice = (EditText)findViewById(R.id.ProductSalePrice);
        EditText prodDesc = (EditText)findViewById(R.id.ProductDescription);
        EditText prodImg = (EditText)findViewById(R.id.ProductImage);

        String txtName = prodName.getText().toString();
        String txtPrice = prodPrice.getText().toString();
        String txtSalePrice = prodSalesPrice.getText().toString();
        String txtDesc = prodDesc.getText().toString();
        String txtImg = prodImg.getText().toString();

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
            boolean isFeatured = false;
            CheckBox isFeaturedProd = findViewById(R.id.IsFeaturedCkbx);
            if (isFeaturedProd.isChecked()){
                isFeatured = true;
            }
            else {
                isFeatured = false;
            }

            Product updatedProduct = new Product(txtName, productPrice, productSalesPrice, txtDesc,spinnerResult,isFeatured,null);
                AppDatabase.getAppDatabase(this).productDao().update(updatedProduct);
                //AppDatabase.getAppDatabase(this).productDao().insert(new Product(txtName, productPrice, productSalesPrice, txtDesc, spinnerResult, isFeatured, null));
                finish();
            }
    }
}
