package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.rollingpinbakery.rollingpinbakery.Weather.CurrentWeatherForecast;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SanFranciscoCurrentWeatherActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth firebaseAuth;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_francisco_current_weather);
        setupTask();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth=FirebaseAuth.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupTask();
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


    /*
    The following 4 methods:
    setupTask
    GetCurrentForecastTask
    GetCurrentForecast
    DisplayCurrentForecast

    All handle getting the current weather from the API

     */

    //Start to get Current Forcast by setting the zipcode
    public void setupTask(){

        String zipcode = "94102";
        new SanFranciscoCurrentWeatherActivity.GetCurrentForecastTask().execute(
                "http://api.apixu.com/v1/forecast.json?key=b7ef6b8e34374af4992141448171112&q=" +
                        zipcode + "&days=14");
    }

    private class GetCurrentForecastTask extends AsyncTask<String, Void, CurrentWeatherForecast> {
        @Override
        protected CurrentWeatherForecast doInBackground(String... strings){
            return GetCurrentForecast(strings[0]);
        }
        protected void onPostExecute(CurrentWeatherForecast result){
            try{
                DisplayCurrentForecast(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public CurrentWeatherForecast GetCurrentForecast(String address){
        URL url = null;
        try{
            url = new URL(address);
        }catch (MalformedURLException ex){
            ex.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try{
            urlConnection = (HttpURLConnection)url.openConnection();
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            InputStream content = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        finally {
            urlConnection.disconnect();
        }

        Gson gson = new Gson();
        return gson.fromJson(stringBuilder.toString(), CurrentWeatherForecast.class);

    }

    //Method to handle displaying the current weather values on the current view
    private void DisplayCurrentForecast(CurrentWeatherForecast currentWeatherForecast){
        TextView title, date, condition, temp, wind, winddirection, feelslike;
        ImageView imageView = findViewById(R.id.imageView);

        title = findViewById(R.id.title);
        title.setText(currentWeatherForecast.getLocation().getName());

        date = findViewById(R.id.date);
        date.setText("Last updated " + currentWeatherForecast.getCurrent().getLastUpdated());

        condition = findViewById(R.id.condition);
        condition.setText("Condition: " + currentWeatherForecast.getCurrent().getCondition().getText());

        temp = findViewById(R.id.temp);
        temp.setText("Temp: " + currentWeatherForecast.getCurrent().getTempF() + "°F");

        wind = findViewById(R.id.wind);
        wind.setText("Wind: " + currentWeatherForecast.getCurrent().getWindMph()+ " MPH");

        winddirection = findViewById(R.id.windDirection);
        winddirection.setText("Wind Direction: " + currentWeatherForecast.getCurrent().getWindDir());

        feelslike = findViewById(R.id.feelsLike);
        feelslike.setText("Feels Like: " + currentWeatherForecast.getCurrent().getFeelslikeF() + "°F");

        Picasso.with(this).load("http:" +currentWeatherForecast.getCurrent().getCondition().getIcon()).into(imageView);

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
        }  else if (id == R.id.nav_Admin) {
            Intent editIntent = new Intent(this, AdminMainActivity.class);
            startActivity(editIntent);
        }else if (id == R.id.nav_Cart) {
            Intent editIntent = new Intent(this, CartActivity.class);
            startActivity(editIntent);
        }else if (id == R.id.nav_Logout) {
            firebaseAuth.signOut();
            finish();
            //SharedPreferences.Editor editor = sharedPreferences.edit();
            //editor.putString("LoginStatus","Logged Out");
            //editor.commit();
            startActivity(new Intent(this, Login.class));
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
