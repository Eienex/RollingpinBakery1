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

            //Button continueButton = findViewById(R.id.paymentsButton);

            String nameText = name.getText().toString();
            String addressText = address.getText().toString();
            String cityText = city.getText().toString();
            String stateText = state.getText().toString();
            String zipCodeText = zipCode.getText().toString();

            AppDatabase.getAppDatabase(this).shippingInfoDao().insert(
                    new ShippingInfo(nameText, addressText, cityText,
                            stateText, zipCodeText));

            startActivity(new Intent(this, PaymentInfo.class));

        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
