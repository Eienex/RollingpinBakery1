package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.Product;
import com.rollingpinbakery.rollingpinbakery.Data.ShippingInfo;

import java.util.ArrayList;

public class ShippingInfoPage extends AppCompatActivity {

    EditText name, address, city, state, zipCode;
    String nameText, addressText, cityText, stateText, zipCodeText;
    Intent shippingResults;
    ArrayList<Cart> carts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shippinginfo);

        name = findViewById(R.id.editName);
        address = findViewById(R.id.editAddress);
        city = findViewById(R.id.editCity);
        state = findViewById(R.id.editState);
        zipCode = findViewById(R.id.editZip);

        carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(this)
                .cartDao()
                .getAllCartItems();

        shippingResults = new Intent(getApplicationContext(), PaymentInfo.class);

        //get all of the cart products and retrieve their values
        // and pass add them to the next Intent result
        for(int i=0; i <carts.size(); i++){
            Bundle extras = getIntent().getExtras();
            String productNum = ("Product").concat(Integer.toString(i));
            String priceNum = ("Price").concat(Integer.toString(i));
            String prodName = extras.getString(productNum);
            String prodPrice = extras.getString(priceNum);
            shippingResults.putExtra(productNum, prodName);
            shippingResults.putExtra(priceNum,prodPrice);
        }
    }

    public void SubmitShippingInfo(View v) {
        //Gets values
        try {
            if(validate()){
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String custID =user.getUid();
                //String custType = spinner.getSelectedItem().toString();
                String shippingID = myRef.push().getKey();

                ShippingInfo shippingInfo = new ShippingInfo(shippingID,custID, nameText, addressText, cityText, stateText, zipCodeText);
                myRef.child("Shipping").child(shippingID).setValue(shippingInfo);
                startActivity(shippingResults);
            }
                   // AppDatabase.getAppDatabase(this).shippingInfoDao().insert(
                       //     new ShippingInfo(nameText, addressText, cityText,
                     //               stateText, zipCodeText));

        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    private Boolean validate(){

        Boolean result = false;

        nameText = name.getText().toString();
        addressText = address.getText().toString();
        cityText = city.getText().toString();
        stateText = state.getText().toString();
        zipCodeText = zipCode.getText().toString();

        if(nameText.isEmpty() || addressText.isEmpty() || cityText.isEmpty() || stateText.isEmpty() || zipCodeText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill out the Shipping form correctly", Toast.LENGTH_SHORT).show();
        }
        else {
            if (stateText.length() != 2) {
                Toast.makeText(getApplicationContext(), "State can only be two letters. EX: MN", Toast.LENGTH_SHORT).show();
            } else if (zipCodeText.length() != 5) {
                Toast.makeText(getApplicationContext(), "Please enter a valid Zip Code", Toast.LENGTH_SHORT).show();
            } else {
                    //put extras to the next form
                    shippingResults.putExtra("ShippingName", nameText);
                    shippingResults.putExtra("ShippingAddress", addressText);
                    shippingResults.putExtra("ShippingCity", cityText);
                    shippingResults.putExtra("ShippingState", stateText);
                    shippingResults.putExtra("ShippingZip", zipCodeText);
                result = true;
            }
        }
        return result;
    }

    public void BackToCart(View view) {
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.CartActivity"));
    }
}
