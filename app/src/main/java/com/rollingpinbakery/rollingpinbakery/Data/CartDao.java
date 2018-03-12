package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by defco on 3/12/2018.
 */
@Dao
public interface CartDao {

    @Query("SELECT * FROM cart")
    public List<Cart> getAllCartItems();

    @Delete()
    public void delete(Cart cart);

    @Insert()
    public void insert(Cart cart);
    @Query("DELETE FROM cart")
    public void removeCart();
}
