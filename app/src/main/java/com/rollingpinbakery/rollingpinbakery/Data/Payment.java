package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by jamej on 3/15/2018.
 */
@Entity
public class Payment {

    @NonNull
    @PrimaryKey
    private String _paymentID;

    @ColumnInfo(name = "custID")
    private String custID;

    @ColumnInfo(name = "payType")
    private String payType;

    @ColumnInfo(name = "cardNum")
    private String cardNum;

    @ColumnInfo(name = "cardHolderName")
    private String cardHolderName;

    @ColumnInfo(name = "expDate")
    private String expDate;

    @ColumnInfo(name = "secCode")
    private String secCode;

    public Payment(){}

    public Payment(String _paymentID, String custID, String payType, String cardNum, String cardHolderName, String expDate, String secCode) {
        this._paymentID = _paymentID;
        this.custID = custID;
        this.payType = payType;
        this.cardNum = cardNum;
        this.cardHolderName = cardHolderName;
        this.expDate = expDate;
        this.secCode = secCode;
    }

    public String get_paymentID() {return _paymentID;}
    public void set_paymentID(String _paymentID) {this._paymentID = _paymentID;}

    public String getCustID() {return custID;}
    public void setCustID(String custID) {this.custID = custID;}

    public String getPayType() {return payType;}
    public void setPayType(String payType) {this.payType = payType;}

    public String getCardNum(){return cardNum;}
    public void setCardNum(String cardNum) {this.cardNum = cardNum;}

    public String getCardHolderName() {return cardHolderName;}
    public void setCardHolderName(String cardHolderName) {this.cardHolderName = cardHolderName;}

    public String getExpDate() {return expDate;}
    public void setExpDate(String expDate) {this.expDate = expDate;}

    public String getSecCode() {return secCode;}
    public void setSecCode(String secCode) {this.secCode = secCode;}


}
