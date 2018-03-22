package com.rollingpinbakery.rollingpinbakery.Weather;

/**
 * Created by rudst on 3/20/18
 */

import java.util.ArrayList;


public class Forecast {
    private ArrayList<ForecastDay> forecastday = new ArrayList<>();

    public Forecast() {}

    public ArrayList<ForecastDay> getForecastday(){
        return forecastday;
    }

    public void setForecastday(ArrayList<ForecastDay> mForecastday){
        this.forecastday = mForecastday;
    }
}
