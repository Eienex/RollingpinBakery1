package com.rollingpinbakery.rollingpinbakery.Data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
/**
 * Created by rudst on 2/12/2018.
 */

@Dao
public interface CustomerDao {

    @Insert()
    public void insert(Customer customer);

    @Update()
    public void update(Customer customer);

    @Delete()
    public void delete(Customer customer);

    @Delete()
    public void deleteAll(List<Customer> customerList);

    @Query("SELECT * FROM customer where _custId == :id")
    public Customer getCustomerById(int id);

    @Query("SELECT * FROM customer where custUsername == :custUsername")
    public Customer getCustomerByUsername(String custUsername);

    @Query("SELECT * FROM customer")
    public List<Customer> getAllCustomers();

    @Query("SELECT * FROM customer where custUsername == :custUsername AND custPassword == :custPassword")
    public Customer getCustomerInfo(String custUsername, String custPassword);

    @Query("UPDATE customer SET custType = 'Admin' where custUsername == :custUsername")
    public void createAdminByUsername(String custUsername);




    //@Query("UPDATE contact set custFName = :custFName where _custId == :id")
    //public void updateCustomerById(int id);
}
