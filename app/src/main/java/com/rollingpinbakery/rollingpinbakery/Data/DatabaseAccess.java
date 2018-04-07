package com.rollingpinbakery.rollingpinbakery.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }





    /**
     The following 6 methods handle all of the Customer Database related operations
     */
    public void insertCustomer(Customer c){
        database.execSQL("INSERT INTO Customers (custFName, custLName, custUsername, custPassword, custEmail, custType)" +
                " Values('" + c.getCustFName() +
                "', '" + c.getCustLName() + "', '" + c.getCustUsername() + "', '" +
                c.getCustPassword() + "', '" + c.getCustEmail() + "', '" + c.getCustType() + "')");
    }

    public void updateCustomer(Customer c){
        database.execSQL("UPDATE Customers SET custFName = '" + c.getCustFName() + "', " +
                "custLName = '" + c.getCustLName() + "', " + "custUsername = '" + c.getCustUsername() +
                "'," + " custPassword = '" + c.getCustPassword() + "'," + " custEmail = '" + c.getCustEmail() +
                "'," + " custType = '" + c.getCustType() + "'" +" Where custID = " + c.get_custId());
    }

    public void deleteCustomer(Customer c){
        database.execSQL("DELETE FROM Customers where custID = " + c.get_custId());
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> list = new ArrayList<Customer>();
        Cursor cursor = database.rawQuery("SELECT * FROM Customers", null);
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            Customer customer = new Customer();
            customer.set_custId(cursor.getInt(0));
            customer.setCustFName(cursor.getString(1));
            customer.setCustLName(cursor.getString(2));
            customer.setCustUsername(cursor.getString(3));
            customer.setCustPassword(cursor.getString(4));
            customer.setCustEmail(cursor.getString(5));
            customer.setCustType(cursor.getString(6));
            list.add(i, customer);
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Customer getCustomerById(int id){
        Customer customer = new Customer();
        Cursor cursor = database.rawQuery("SELECT * FROM Customers where custId == " + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            customer.set_custId(cursor.getInt(0));
            customer.setCustFName(cursor.getString(1));
            customer.setCustLName(cursor.getString(2));
            customer.setCustUsername(cursor.getString(3));
            customer.setCustPassword(cursor.getString(4));
            customer.setCustEmail(cursor.getString(5));
            customer.setCustType(cursor.getString(6));
            cursor.moveToNext();
        }
        cursor.close();
        return customer;
    }

    public Customer getCustomerByUsername(String custUsername){
        Customer customer = new Customer();
        Cursor cursor = database.rawQuery("SELECT * FROM Customers where custUsername = '" + custUsername + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            customer.set_custId(cursor.getInt(0));
            customer.setCustFName(cursor.getString(1));
            customer.setCustLName(cursor.getString(2));
            customer.setCustUsername(cursor.getString(3));
            customer.setCustPassword(cursor.getString(4));
            customer.setCustEmail(cursor.getString(5));
            customer.setCustType(cursor.getString(6));
            cursor.moveToNext();
        }
        cursor.close();
        return customer;
    }

    public Customer getCustomerInfo(String custUsername, String custPassword){
        Customer customer = new Customer();
        Cursor cursor = database.rawQuery("SELECT * FROM Customers where custUsername = '" + custUsername + "' AND custPassword = '" + custPassword + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            customer.set_custId(cursor.getInt(0));
            customer.setCustFName(cursor.getString(1));
            customer.setCustLName(cursor.getString(2));
            customer.setCustUsername(cursor.getString(3));
            customer.setCustPassword(cursor.getString(4));
            customer.setCustEmail(cursor.getString(5));
            customer.setCustType(cursor.getString(6));
            cursor.moveToNext();
        }
        cursor.close();
        return customer;
    }




    /**
     The following 6 methods handle all of the Product Database related operations
     */

    public void insertProduct(Product p){
        database.execSQL("INSERT INTO Products (prodName, prodRetailPrice, prodSalesPrice, prodDesc, prodType, prodFeatured, prodImg)" +
                " Values('" + p.getProdName() +
                "', " + p.getProdRetailPrice() + ", " + p.getProdSalePrice() + ", '" +
                p.getProdDesc() + "', '" + p.getProdType() + "', " + p.getProdFeatured() + ", '" + p.getProdImage()+ "')");
    }

    public void updateProduct(Product p){
        database.execSQL("UPDATE Products SET prodName = '" + p.getProdName() + "', " +
                "prodRetailPrice = " + p.getProdRetailPrice() + ", " + "prodSalesPrice = " + p.getProdSalePrice()+
                "," + " prodDesc = '" + p.getProdDesc() + "'," + " prodType = '" + p.getProdType() +
                "'," + " prodFeatured = " + p.getProdFeatured() + ", " + "prodImg = '" + p.getProdImage() +
                "' Where prodID = " + p.get_prodId());
    }

    public void deleteProduct(Product p){
        database.execSQL("DELETE FROM Products where prodID = " + p.get_prodId());
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> list = new ArrayList<Product>();
        Cursor cursor = database.rawQuery("SELECT * FROM Products", null);
        cursor.moveToFirst();
        int i = 0;
        while (!cursor.isAfterLast()) {
            list.add(i, new Product(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2),
                            cursor.getDouble(3), cursor.getString(4), cursor.getString(5),
                            cursor.getInt(6), cursor.getString(7)));
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Product getProductById(int id){
        Product product = new Product();
        Cursor cursor = database.rawQuery("SELECT * FROM Products where prodID == " + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product.set_prodId(cursor.getInt(0));
            product.setProdName(cursor.getString(1));
            product.setProdRetailPrice(cursor.getDouble(2));
            product.setProdSalePrice(cursor.getDouble(3));
            product.setProdDesc(cursor.getString(4));
            product.setProdType(cursor.getString(5));
            product.setProdFeatured(cursor.getInt(6));
            product.setProdImage(cursor.getString(7));
            cursor.moveToNext();
        }
        cursor.close();
        return product;
    }

    public List<Product> getProductByType(String type){
        ArrayList<Product> list = new ArrayList<Product>();
        Cursor cursor = database.rawQuery("SELECT * FROM Products where prodType = '" + type + "'", null);
        cursor.moveToFirst();
        int i =0;
        while (!cursor.isAfterLast()) {
            list.add(i, new Product(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2),
                    cursor.getDouble(3), cursor.getString(4), cursor.getString(5),
                    cursor.getInt(6), cursor.getString(7)));
            i++;
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    /**
     The following methods handle all of the Order Database related operations
     */
}