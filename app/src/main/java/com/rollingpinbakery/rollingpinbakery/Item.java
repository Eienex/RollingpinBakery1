package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

import java.util.ArrayList;

/**
 * Created by equno_000 on 2/25/2018.
 */

public class Item extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;

    ListView listView;
    ArrayList<Product> products;
    private static ItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //get shared Preferences
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String LoginStatus = sharedPreferences.getString("LoginStatus","");
        String UserRole = sharedPreferences.getString("UserRole", "");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //check to see what the login status of the current user is
        if (LoginStatus.equals("Logged In")){//if the user is logged in
            if(UserRole.equals("Admin")){//if the user is an admin
                navigationView.getMenu().clear();
                //set the navView to the Admin View
                navigationView.inflateMenu(R.menu.activity_main_admin_drawer);
            }
            else {//if the user is not an Admin
                navigationView.getMenu().clear();
                //set the nav view to the Main Logged In View
                navigationView.inflateMenu(R.menu.activity_main_logged_in_drawer);
            }
        }
        else{//If the user is not logged in
            navigationView.getMenu().clear();
            //set the nav view to the Guest View
            navigationView.inflateMenu(R.menu.activity_main_guest_drawer);
        }

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
        getMenuInflater().inflate(R.menu.store, menu);
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
        }  else if (id == R.id.nav_Account) {
            Intent editIntent = new Intent(this, Account.class);
            startActivity(editIntent);
        }  else if (id == R.id.nav_Register) {
            Intent editIntent = new Intent(this, Register.class);
            startActivity(editIntent);
        }  else if (id == R.id.nav_Cart) {
            Intent editIntent = new Intent(this, CartActivity.class);
            startActivity(editIntent);
        }  else if (id == R.id.nav_Logout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LoginStatus","Logged Out");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "You have successfully Logged Out!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_Locations) {
            Intent editIntent = new Intent(this, Locations.class);
            startActivity(editIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onResume() {
        super.onResume();
        listView = findViewById(R.id.listView);
        products = (ArrayList<Product>) AppDatabase.getAppDatabase(this).productDao().getAllProducts();
        adapter = new ItemAdapter(this, products);
        listView.setAdapter(adapter);
    }

}

