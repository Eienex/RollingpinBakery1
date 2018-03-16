package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jamej on 3/15/2018.
 */
@Entity
public class Payment {

    @PrimaryKey(autoGenerate = true)
    private int _paymentID;

    @ColumnInfo(name = "custID")
    private int custID;

    @ColumnInfo(name = "payType")
    private String payType;

    @ColumnInfo(name = "cardNum")
    private int cardNum;

    @ColumnInfo(name = "cardHolderName")
    private String cardHolderName;

    @ColumnInfo(name = "expDate")
    private String expDate;

    @ColumnInfo(name = "secCode")
    private int secCode;


    public Payment(int _paymentID, int custID, String payType, int cardNum, String cardHolderName, String expDate, int secCode) {
        this._paymentID = _paymentID;
        this.custID = custID;
        this.payType = payType;
        this.cardNum = cardNum;
        this.cardHolderName = cardHolderName;
        this.expDate = expDate;
        this.secCode = secCode;
    }

    public int get_paymentID() {return _paymentID;}
    public void set_paymentID(int _paymentID) {this._paymentID = _paymentID;}

    public int getCustID() {return custID;}
    public void setCustID(int custID) {this.custID = custID;}

    public String getPayType() {return payType;}
    public void setPayType(String payType) {this.payType = payType;}

    public int getCardNum(){return cardNum;}
    public void setCardNum(int cardNum) {this.cardNum = cardNum;}

    public String getCardHolderName() {return cardHolderName;}
    public void setCardHolderName(String cardHolderName) {this.cardHolderName = cardHolderName;}

    public String getExpDate() {return expDate;}
    public void setExpDate(String expDate) {this.expDate = expDate;}

    public int getSecCode() {return secCode;}
    public void setSecCode(int secCode) {this.secCode = secCode;}


}
