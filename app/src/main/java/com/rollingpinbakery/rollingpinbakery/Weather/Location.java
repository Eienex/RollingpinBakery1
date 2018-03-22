package com.rollingpinbakery.rollingpinbakery.Weather;

/**
 * Created by rudst on 3/20/18
 */

public class Location {
    private Long id;
    private String name;
    private String region;
    private String country;
    private String tz_id;
    private String localtime;
    private double lat;
    private double lon;
    private int localtime_epoch;

    public Location() {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String mName)
    {
        this.name = mName;
    }

    public Long getId() {
        return id;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String mRegion)
    {
        this.region = mRegion;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String mCountry)
    {
        this.country = mCountry;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double mLat)
    {
        this.lat = mLat;
    }

    public double getLong()
    {
        return lon;
    }

    public void setLong(double mLong)
    {
        this.lon = mLong;
    }

    public String getTzId()
    {
        return tz_id;
    }

    public void setTzId(String mTz_id)
    {
        this.tz_id = mTz_id;
    }

    public int getLocaltimeEpoch()
    {
        return localtime_epoch;
    }

    public void setLocaltimeEpoch(int mLocaltimeEpoch)
    {
        this.localtime_epoch = mLocaltimeEpoch;
    }

    public String getLocaltime()
    {
        return localtime;
    }

    public void setLocaltimeEpoch(String mLocaltime)
    {
        this.localtime = mLocaltime;
    }
}
