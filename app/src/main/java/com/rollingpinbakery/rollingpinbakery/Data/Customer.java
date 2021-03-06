package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import java.sql.Date;



/**
 * Created by rudst on 2/12/2018.
 */

@Entity
public class Customer {

    @NonNull
    @PrimaryKey
    private String _custId;

    @ColumnInfo(name = "custFname")
    private String custFName;

    @ColumnInfo(name = "custLName")
    private String custLName;

    @ColumnInfo(name = "custUsername")
    private String custUsername;

    @ColumnInfo(name = "custEmail")
    private String custEmail;

    @ColumnInfo(name = "custPassword")
    private String custPassword;

    @ColumnInfo(name = "custType")
    private String custType;

    //@ColumnInfo(name = "dateSignedUp")
    //@TypeConverter({Converters.class})
    //private Date dateSignedUp;

    public Customer(){}

    @Ignore
    public Customer( String custFName, String custLName, String custUsername, String custEmail, String custPassword, String custType) {

        this.custFName = custFName;
        this.custLName = custLName;
        this.custUsername = custUsername;
        this.custEmail = custEmail;
        this.custPassword = custPassword;
        this.custType = custType;
        //this.dateSignedUp = dateSignedUp;
    }

    public Customer(String id, String custFName, String custLName, String custUsername, String custPassword, String custEmail, String custType) {
        this._custId = id;
        this.custFName = custFName;
        this.custLName = custLName;
        this.custUsername = custUsername;
        this.custPassword = custPassword;
        this.custEmail = custEmail;
        this.custType = custType;
        //this.dateSignedUp = dateSignedUp;
    }

    public Customer(String id, String custFName, String custLName, String custEmail, String custType) {
        this._custId = id;
        this.custFName = custFName;
        this.custLName = custLName;
        this.custEmail = custEmail;
        this.custType = custType;
    }

    public String get_custId() {return _custId;}
    public void set_custId(String _custId) {this._custId = _custId;}

    public String getCustFName() {return custFName;}
    public void setCustFName(String custFName) {this.custFName = custFName;}

    public String getCustLName() {return custLName;}
    public void setCustLName(String custLName) {this.custLName = custLName;}

    public String getCustUsername() {return custUsername;}
    public void setCustUsername(String custUsername) {this.custUsername = custUsername;}

    public String getCustPassword() {return custPassword;}
    public void setCustPassword(String custPassword) {this.custPassword = custPassword;}

    public String getCustEmail() {return custEmail;}
    public void setCustEmail(String custEmail) {this.custEmail = custEmail;}

    public String getCustType() {return custType;}
    public void setCustType(String custtype) {this.custType = custtype;}
    //public Date getDateSignedUp() {return dateSignedUp;}
    //public void setDateSignedUp(Date dateSignedUp) {this.dateSignedUp = dateSignedUp;}

}
