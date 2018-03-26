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
public interface PaymentDao {

    @Insert()
    public void insert(Payment payment);

    @Update()
    public void update(Payment payment);

    @Delete()
    public void delete(Payment payment);

    @Delete()
    public void deleteAll(List<Payment> paymentList);

    @Query("SELECT * FROM payment where _paymentID == :id")
    public Payment getPaymentsById(int id);

    @Query("SELECT * FROM payment")
    public List<Payment> getAllPayments();

}
