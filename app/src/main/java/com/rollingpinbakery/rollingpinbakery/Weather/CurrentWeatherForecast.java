package com.rollingpinbakery.rollingpinbakery.Weather;

/**
 * Created by rudst on 3/20/18
 */

public class CurrentWeatherForecast {

    private Current current;
    private Location location;
    private String last_updated;
    private String wind_dir;

    public String getLast_Updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
