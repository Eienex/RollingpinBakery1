package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import java.util.Date;


/**
 * Created by jamej on 3/15/2018.
 */
@Entity
public class OrderDetails {

    @PrimaryKey(autoGenerate = true)
    private int _orderDetailNum;

    @ColumnInfo(name = "orderID")
    private int orderID;

    @ColumnInfo(name = "productID")
    private int productID;

    @ColumnInfo(name = "qtyOrdered")
    private int qtyOrdered;

    @ColumnInfo(name = "totalAmount")
    private double totalAmount;

    //@ColumnInfo(name = "orderDate")
    //private Date orderDate;

    public OrderDetails(int _orderDetailNum, int orderID, int productID, int qtyOrdered, double totalAmount)//, Date orderDate)
    {
        this._orderDetailNum = _orderDetailNum;
        this.orderID = orderID;
        this.productID = productID;
        this.qtyOrdered = qtyOrdered;
        this.totalAmount = totalAmount;
        //this.orderDate = orderDate;
    }
    public int get_orderDetailNum() {return _orderDetailNum;}
    public void set_orderDetailNum(int _orderDetailNum) {this._orderDetailNum = _orderDetailNum;}

    public int getOrderID(){return orderID;}
    public void setOrderID(int orderID){this.orderID = orderID;}

    public int getProductID(){return productID;}
    public void setProductID(int productID){this.productID = productID;}

    public int getQtyOrdered(){return qtyOrdered;}
    public void setQtyOrdered(int qtyOrdered){this.qtyOrdered = qtyOrdered;}

    public double getTotalAmount() {return totalAmount;}
    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    //public Date getOrderDate() {return orderDate;}
    //public void setOrderDate(Date orderDate) {this.orderDate = orderDate;}
}

