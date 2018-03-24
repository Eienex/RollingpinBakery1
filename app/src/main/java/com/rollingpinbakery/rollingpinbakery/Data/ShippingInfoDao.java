package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by jamej on 3/23/2018.
 */
@Dao
public interface ShippingInfoDao {

    @Insert()
    public void insert(ShippingInfo shippingInfo);

    @Update()
    public void update(ShippingInfo shippingInfo);

    @Delete()
    public void delete(ShippingInfo shippingInfo);

    @Delete()
    public void deleteAll(List<ShippingInfo> shippingInfoList);


    @Query("SELECT * FROM shippingInfo WHERE name == :name")
    public ShippingInfo getShippingInfoByName(String name);

    @Query("SELECT * FROM shippingInfo")
    public List<ShippingInfo> getAllShippingInfo();
}
