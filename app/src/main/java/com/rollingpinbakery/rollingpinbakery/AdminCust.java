package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.util.ArrayList;
import java.util.List;

public class AdminCust extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth firebaseAuth;


    ListView listView;
    ArrayList<Customer> customers;
    private static CustAdapter adapter;

    DatabaseReference dbReference;
    //Customer customer;
    //List<String> itemList;
    TextView name,userName, email, password, custType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customers);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AdminCustomerAdd.class));
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

      listView = findViewById(R.id.listView);
      customers = new ArrayList<>();

      //itemList = new ArrayList<>();

      dbReference = FirebaseDatabase.getInstance().getReference("users");
      dbReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              for(DataSnapshot snapshot : dataSnapshot.getChildren())
              {
                  Customer customer = snapshot.getValue(Customer.class);

                  String custFName = customer.getCustFName();
                  String custLName = customer.getCustLName();
                  String userName = customer.getCustUsername();
                  String custEmail = customer.getCustEmail();
                  String custPassword = customer.getCustPassword();
                  String custType = customer.getCustType();


                  customers.add(new Customer(custFName, custLName,userName, custEmail, custPassword, custType));

              }
              adapter = new CustAdapter(customers, getApplicationContext());
              listView.setAdapter(adapter);

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String userID = user.getUid();
                Customer customer = dataSnapshot.child(userID).getValue(Customer.class);
                String type = customer.getCustType();
                //String userID = user.getUid();



                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        AdminCust.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();
                NavigationView navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(AdminCust.this);

                if (type.equals("Admin")) {//if the user is an admin
                    navigationView.getMenu().clear();
                    //set the navView to the Admin View
                    navigationView.inflateMenu(R.menu.activity_main_admin_drawer);
                } else {//if the user is not an Admin
                    navigationView.getMenu().clear();
                    //set the nav view to the Main Logged In View
                    navigationView.inflateMenu(R.menu.activity_main_logged_in_drawer);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
            }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            Intent editIntent = new Intent(this, MainActivity.class);
            startActivity(editIntent);
        } else if (id == R.id.nav_Store) {
            Intent editIntent = new Intent(this, Store.class);
            startActivity(editIntent);
        }else if (id == R.id.nav_Admin) {
            Intent editIntent = new Intent(this, AdminMainActivity.class);
            startActivity(editIntent);
        } else if (id == R.id.nav_Locations) {
            Intent editIntent = new Intent(this, Locations.class);
            startActivity(editIntent);
        }else if (id == R.id.nav_Logout) {
            firebaseAuth.signOut();
            finish();
            //SharedPreferences.Editor editor = sharedPreferences.edit();
            //editor.putString("LoginStatus","Logged Out");
            //editor.commit();
            startActivity(new Intent(this, Login.class));
            Toast.makeText(getApplicationContext(), "You have successfully Logged Out!", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

