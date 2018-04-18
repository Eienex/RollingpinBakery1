package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by defco on 3/12/2018.
 */
@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int _cartId;



    @ColumnInfo(name = "prodID")
    private String prodID;

    @ColumnInfo(name = "itemName")
    private String itemName;

    @ColumnInfo(name = "itemCategory")
    private String itemCat;

    @ColumnInfo(name = "Price")
    private String price;

    @ColumnInfo(name = "Qty")
    private int qty;

    public Cart(String prodID, String itemName, String itemCat, String price, int qty) {
        this.prodID = prodID;
        this.itemName = itemName;
        this.itemCat = itemCat;
        this.price = price;
        this.qty = qty;
    }

/*
    public Cart(int cartId, String itemName, String itemCat, String price) {
        this._cartId = cartId;
        this.itemName = itemName;
        this.itemCat = itemCat;
        this.price = price;
    }
    */

    public int get_cartId() {return _cartId;}
    public void set_cartId(int _cartId) {this._cartId = _cartId;}

    public String getProdID() {return prodID; }
    public void setProdID(String prodID) {this.prodID = prodID; }

    public String getItemName() {return itemName;}
    public void setItemName(String itemName) {this.itemName = itemName;}

    public String getItemCat() {return itemCat;}
    public void setItemCat(String itemCat) {this.itemCat = itemCat;}

    public String getPrice() {return price;}
    public void setPrice(String price) {this.price = price;}

    public int getQty() {return qty;}
    public void setQty(int qty) {this.qty = qty;}
}
