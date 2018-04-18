package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

public class Admin_AddProduct extends AppCompatActivity{

    String[] productTypes;

    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
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
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {  }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {  }
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
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    //FirebaseUser productID = firebaseAuth.getCurrentUser();
                    String productID = myRef.push().getKey();
                    // String productType = spinner.getSelectedItem().toString();

                    Product product = new Product(productID, txtName, productPrice,productSalesPrice, txtDesc, spinnerResult, isFeatured, null);
                    myRef.child("Products").child(productID).setValue(product);

                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(new Intent(this, Admin_Products.class));

            }
            else {
                try{
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    //FirebaseUser productID = firebaseAuth.getCurrentUser();
                    //String productIDUid =productID.getUid();
                    String productID = myRef.push().getKey();

                    Product product = new Product(productID, txtName, productPrice, productSalesPrice, txtDesc, spinnerResult, isFeatured, null);
                    myRef.child("Products").child(productID).setValue(product);
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(new Intent(this, Admin_Products.class));
            }
        }

    }


}
