package com.rollingpinbakery.rollingpinbakery.Weather;

/**
 * Created by rudst on 3/20/18
 */


public class ForecastDay {
    private String date;
    private Day day = new Day();
    public ForecastDay() {

    }


    public String getDate() {  return date;}



    public void setDate(String mDate) {this.date = mDate;}

    public Day getDay() {return day; }

    public void setDay(Day mDay){this.day = mDay;}

}
