package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by jamej on 3/23/2018.
 */
@Entity
public class ShippingInfo {
    @NonNull
    @PrimaryKey
    private String _id;

    @ColumnInfo(name = "custID")
    private String custID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "state")
    private String state;

    @ColumnInfo(name = "zipCode")
    private String zipCode;

    @Ignore
    public ShippingInfo(){}


    public ShippingInfo(String _id, String custID, String name, String address, String city, String state, String zipCode)
    {
        this._id = _id;
        this.custID = custID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    @Ignore
    public ShippingInfo(String custID,String name, String address, String city, String state, String zipCode)
    {
        this.custID = custID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String get_id(){return _id;}
    public void set_id(String _id){this._id = _id;}

    public String getCustID(){return custID;}
    public void setCustID(String custID){this._id = _id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}

    public String getState() {return state;}
    public void setState(String state) {this.state = state;}

    public String getZipCode() {return zipCode;}
    public void setZipCode(String zipCode) {this.zipCode = zipCode;}
}
