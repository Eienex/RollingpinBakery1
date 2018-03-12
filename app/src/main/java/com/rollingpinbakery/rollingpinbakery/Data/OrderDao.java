package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert()
    public void insert(Order order);

    @Update()
    public void update(Order order);

    @Delete()
    public void delete(Order order);

    @Delete()
    public void deleteAll(List<Order> orderList);

    @Query("SELECT * FROM `order` where _orderId == :id")
    public Order getOrderById(int id);

    @Query(value = "SELECT * FROM 'order'")
    public List<Order> getAllOrders();


    @Query("SELECT _orderId, custId, paymentId FROM 'order'")

    public List<Order> getOrderReports();

}