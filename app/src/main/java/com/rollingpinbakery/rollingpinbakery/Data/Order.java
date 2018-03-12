package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jamej on 3/11/2018.
 */
@Entity
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int _orderId;

    @ColumnInfo(name = "paymentId")
    private int paymentId;

    @ColumnInfo(name = "custId")
    private int custId;
    
    public Order(int _orderId, int paymentId, int custId) {
        this._orderId = _orderId;
        this.paymentId = paymentId;
        this.custId = custId;
    }

    public int get_orderId() {return _orderId;}
    public void set_orderId(int _orderId) {this._orderId = _orderId;}

    public int getPaymentId() {return paymentId;}
    public void setPaymentId(int paymentId) {this.paymentId = paymentId;}

    public int getCustId() {return custId;}
    public void setCustId(int custId) {this.custId = custId;}

}
