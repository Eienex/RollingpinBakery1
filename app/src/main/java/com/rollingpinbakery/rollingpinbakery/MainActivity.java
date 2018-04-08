package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.LoginActivity"));

            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.Register"));
            }
        });

        //get shared Preferences
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //Get the values from the shared preferences
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
        } else if (id == R.id.nav_Account) {
            Intent editIntent = new Intent(this, LoginActivity.class);
            startActivity(editIntent);
        } else if (id == R.id.nav_Admin) {
            Intent editIntent = new Intent(this, AdminMainActivity.class);
            startActivity(editIntent);
        }else if (id == R.id.nav_Cart) {
            Intent editIntent = new Intent(this, CartActivity.class);
            startActivity(editIntent);
        }else if (id == R.id.nav_Logout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LoginStatus","Logged Out");
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "You have successfully Logged Out!", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_Locations) {
            Intent editIntent = new Intent(this, Locations.class);
            startActivity(editIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
