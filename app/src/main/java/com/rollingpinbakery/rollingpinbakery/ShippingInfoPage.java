package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.ShippingInfo;

public class ShippingInfoPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shippinginfo);
    }

    public void SubmitShippingInfo(View v) {
        //Gets values
        try {

            EditText name = findViewById(R.id.editName);
            EditText address = findViewById(R.id.editAddress);
            EditText city = findViewById(R.id.editCity);
            EditText state = findViewById(R.id.editState);
            EditText zipCode = findViewById(R.id.editZip);



            String nameText = name.getText().toString();
            String addressText = address.getText().toString();
            String cityText = city.getText().toString();
            String stateText = state.getText().toString();
            String zipCodeText = zipCode.getText().toString();

            if(nameText.isEmpty() || addressText.isEmpty() || cityText.isEmpty() || stateText.isEmpty() || zipCodeText.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please fill out the Shipping form correctly", Toast.LENGTH_SHORT ).show();
            }
            else {
                if(stateText.length() != 2){
                    Toast.makeText(getApplicationContext(), "State can only be two letters. EX: MN", Toast.LENGTH_SHORT ).show();
                }
                else if (zipCodeText.length() != 5){
                    Toast.makeText(getApplicationContext(), "Please enter a valid Zip Code", Toast.LENGTH_SHORT ).show();
                }
                else{
                    //put extras to the next form
                    Intent shippingResults = new Intent(getApplicationContext(), PaymentInfo.class);
                    shippingResults.putExtra("ShippingName", nameText);
                    shippingResults.putExtra("ShippingAddress", addressText);
                    shippingResults.putExtra("ShippingCity", cityText);
                    shippingResults.putExtra("ShippingState", stateText);
                    shippingResults.putExtra("ShippingZip", zipCodeText);

                    AppDatabase.getAppDatabase(this).shippingInfoDao().insert(
                            new ShippingInfo(nameText, addressText, cityText,
                                    stateText, zipCodeText));

                    startActivity(shippingResults);
                }
            }
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void BackToCart(View view) {
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.CartActivity"));
    }
}
