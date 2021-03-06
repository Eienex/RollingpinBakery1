package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by rudst on 2/12/2018.
 */

@Entity
public class Product {
    @PrimaryKey
    @NonNull
    private String _prodId;

    @ColumnInfo(name = "prodName")
    private String prodName;

    @ColumnInfo(name = "prodRetailPrice")
    private Double prodRetailPrice;

    @ColumnInfo(name = "prodSalePrice")
    private Double prodSalePrice;

    @ColumnInfo(name = "prodDesc")
    private String prodDesc;

    @ColumnInfo(name = "prodType")
    private String prodType;

    @ColumnInfo(name = "prodFeatured")
    private int prodFeatured;

    @ColumnInfo(name = "prodImage")
    private String prodImage;

    @ColumnInfo(name = "onSale")
    private String onSale;


    public Product(){}

    @Ignore
    public Product(String prodName, Double prodRetailPrice, Double prodSalePrice, String prodDesc, String prodType, int prodFeatured, String prodImage) {

        this.prodName = prodName;
        this.prodRetailPrice = prodRetailPrice;
        this.prodSalePrice = prodSalePrice;
        this.prodDesc = prodDesc;
        this.prodType = prodType;
        this.prodFeatured = prodFeatured;
        this.prodImage = prodImage;
    }

    public Product( String prodName, Double prodRetailPrice, Double prodSalePrice, String onSale, String prodType) {


        this.prodName = prodName;
        this.prodRetailPrice = prodRetailPrice;
        this.prodSalePrice = prodSalePrice;
        this.onSale = onSale;
        this.prodType = prodType;
    }
    public Product(String id, String prodName, Double prodRetailPrice, Double prodSalePrice, String prodDesc, String prodType, int prodFeatured, String prodImage) {

        this._prodId = id;
        this.prodName = prodName;
        this.prodRetailPrice = prodRetailPrice;
        this.prodSalePrice = prodSalePrice;
        this.prodDesc = prodDesc;
        this.prodType = prodType;
        this.prodFeatured = prodFeatured;
        this.prodImage = prodImage;
    }

    public String get_prodId() {return _prodId;}
    public void set_prodId(String _prodId) {this._prodId = _prodId;}

    public String getProdName() {return prodName;}
    public void setProdName(String prodName) {this.prodName = prodName;}

    public Double getProdRetailPrice() {return prodRetailPrice;}
    public void setProdRetailPrice(Double prodRetailPrice) {this.prodRetailPrice = prodRetailPrice;}

    public Double getProdSalePrice() {return prodSalePrice;}
    public void setProdSalePrice(Double prodSalePrice) {this.prodSalePrice = prodSalePrice;}

    public String getProdDesc() {return prodDesc;}
    public void setProdDesc(String prodDesc) {this.prodDesc = prodDesc;}

    public String getProdType() {return prodType;}
    public void setProdType(String prodType) {this.prodType = prodType;}

    public int getProdFeatured() {return prodFeatured;}
    public void setProdFeatured(int prodFeatured) {this.prodFeatured = prodFeatured;}

    public String getProdImage() {return prodImage;}
    public void setProdImage(String prodImage) {this.prodImage = prodImage;}

    public String getOnSale() {return onSale;}
    public void setOnSale(String onSale) {this.onSale = onSale;}

}
