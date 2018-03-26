package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by rudst on 2/12/2018.
 */

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int _prodId;

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
    private Boolean prodFeatured;

    @ColumnInfo(name = "prodImage")
    private String prodImage;

    public Product(String prodName, Double prodRetailPrice, Double prodSalePrice, String prodDesc, String prodType, Boolean prodFeatured, String prodImage) {

        this.prodName = prodName;
        this.prodRetailPrice = prodRetailPrice;
        this.prodSalePrice = prodSalePrice;
        this.prodDesc = prodDesc;
        this.prodType = prodType;
        this.prodFeatured = prodFeatured;
        this.prodImage = prodImage;
    }

    public Product(int id, String prodName, Double prodRetailPrice, Double prodSalePrice, String prodDesc, String prodType, boolean prodFeatured, String prodImage) {

        this._prodId = id;
        this.prodName = prodName;
        this.prodRetailPrice = prodRetailPrice;
        this.prodSalePrice = prodSalePrice;
        this.prodDesc = prodDesc;
        this.prodType = prodType;
        this.prodFeatured = prodFeatured;
        this.prodImage = prodImage;
    }

    public int get_prodId() {return _prodId;}
    public void set_prodId(int _prodId) {this._prodId = _prodId;}

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

    public Boolean getProdFeatured() {return prodFeatured;}
    public void setProdFeatured(Boolean prodFeatured) {this.prodFeatured = prodFeatured;}

    public String getProdImage() {return prodImage;}
    public void setProdImg(String prodImage) {this.prodImage = prodImage;}


}
