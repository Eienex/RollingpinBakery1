package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderConfirmation extends AppCompatActivity {
    ListView listView;
    ArrayList<Cart> carts;
    private static CartProductsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        try{
            listView = findViewById(R.id.listView);
            //customers = (ArrayList<Customer>) AppDatabase.getAppDatabase(this).customerDao().getAllCustomers();
            carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(this).cartDao().getAllCartItems();
            adapter = new CartProductsAdapter(this, carts);
            listView.setAdapter(adapter);
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        TextView productName = findViewById(R.id.prodName);
        TextView productPrice = findViewById(R.id.txtProdPrice);
        TextView cartTotal = findViewById(R.id.OrderTotalText);
        TextView Shippingtotal = findViewById(R.id.orderShip);
        TextView orderTax = findViewById(R.id.orderTax);

        TextView shipNameText = findViewById(R.id.ShipNameText);
        TextView shipAddressText = findViewById(R.id.ShipAddressText);
        TextView shipCityText = findViewById(R.id.ShipCityText);
        TextView shipStateText = findViewById(R.id.ShipStateText);
        TextView shipZipText = findViewById(R.id.ShipZipcodeText);

        TextView payCardNameText = findViewById(R.id.PayCardNameText);
        TextView payCardType = findViewById(R.id.cardTypeText);
        TextView payCardNumber = findViewById(R.id.CardNumText);
        TextView payCardExpText = findViewById(R.id.CardExpText);
        TextView payCardCSCText = findViewById(R.id.CardCSVNumText);

        carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(this)
                .cartDao()
                .getAllCartItems();

        //get all of the cart products and retrieve their values
        // and pass add them to the next Intent result
        double orderTotal = 0.00;
        for(int i=0; i <carts.size(); i++){
            Bundle extras = getIntent().getExtras();
            String productNum = ("Product").concat(Integer.toString(i));
            String priceNum = ("Price").concat(Integer.toString(i));
            String prodName = extras.getString(productNum);
            String prodPrice = extras.getString(priceNum);
            //String qty = carts.get(i).getQty();
            //int qtyI = Integer.getInteger(qty);

            double prodPriced = Double.parseDouble(prodPrice);
            //double productTotal = prodPriced * qtyI;
            //orderTotal += productTotal;
            orderTotal += prodPriced;


        }
            double shippingCharge = 5.99;
            orderTotal += shippingCharge;
            double tax = orderTotal * .07;

            orderTotal += tax;

        Shippingtotal.setText("Shipping: $" + String.valueOf(shippingCharge));
        //orderTax.setText("Tax: $" + String.format("%.2f",String.valueOf(tax)));
        orderTax.setText("Tax: $" + new DecimalFormat("#.00").format(tax));

        //Get all of the form results from the shipping and payments page
        Bundle extras = getIntent().getExtras();
        String ShippingName = extras.getString("ShippingName");
        String ShippingAddress = extras.getString("ShippingAddress");
        String ShippingCity = extras.getString("ShippingCity");
        String ShippingState = extras.getString("ShippingState");
        String ShippingZip = extras.getString("ShippingZip");
        String PaymentName = extras.getString("PaymentName");
        String PaymentCardNum = extras.getString("PaymentCardNumber");
        String PaymentCardType = extras.getString("PaymentCardType");
        String PaymentExpMonth = extras.getString("PaymentExpMonth");
        String PaymentExpYear = extras.getString("PaymentExpYear");
        String PaymentCSCNumber = extras.getString("PaymentCSCNumber");



        //set the textboxes to all of the form info
        cartTotal.setText("Order Total: $" + new DecimalFormat("#.00").format(orderTotal));
        shipNameText.setText("Name: " + ShippingName);
        shipAddressText.setText("Address: " + ShippingAddress);
        shipCityText.setText("City: " + ShippingCity);
        shipStateText.setText("State: " + ShippingState);
        shipZipText.setText("Zip code: " + ShippingZip);
        payCardNameText.setText("Card Holder: " + PaymentName);
        payCardType.setText("Credit Card Type: " + PaymentCardType);
        payCardNumber.setText("Credit Card Number: " + PaymentCardNum);
        payCardExpText.setText("Expiration Date: " + PaymentExpMonth + ", " + PaymentExpYear);
        payCardCSCText.setText("Card CSC Number: " + PaymentCSCNumber);
    }

    //Method to handle the Place Order button
    public void PlaceOrder(View view){
        Toast.makeText(getApplicationContext(), "Thank you for placing an order with Rolling Pin Bakery!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


    @Override
    protected void onResume(){
        super.onResume();
        try{
            listView = findViewById(R.id.listView);
            //customers = (ArrayList<Customer>) AppDatabase.getAppDatabase(this).customerDao().getAllCustomers();
            carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(this).cartDao().getAllCartItems();
            adapter = new CartProductsAdapter(this, carts);
            listView.setAdapter(adapter);
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}

