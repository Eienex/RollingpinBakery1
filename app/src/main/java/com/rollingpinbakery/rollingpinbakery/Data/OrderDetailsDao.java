package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by jamej on 3/15/2018.
 */
@Dao
public interface OrderDetailsDao {

    @Insert()
    public void insert(OrderDetails orderDetails);

    @Update()
    public void update(OrderDetails orderDetails);

    @Delete()
    public void delete(OrderDetails orderDetails);

    @Delete()
    public void deleteAll(List<OrderDetails> orderDetailsList);


    @Query("SELECT * FROM orderdetails WHERE _orderDetailNum == :id")
    public OrderDetails getOrderDetailsById(int id);

    @Query("SELECT * FROM orderdetails")
    public List<OrderDetails> getAllOrderDetails();




}