package com.rollingpinbakery.rollingpinbakery.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

/**
 * Created by rudst on 2/12/2018.
 */
@Dao
public interface ProductDao {
    @Insert()
    public void insert(Product product);

    @Update()
    public void update(Product product);

    @Delete()
    public void delete(Product product);

    @Delete()
    public void deleteAll(List<Product> productList);

    @Query("SELECT * FROM product where _prodId == :id")
    public Product getProductById(int id);

    @Query("SELECT * FROM product")
    public List<Product> getAllProducts();

    @Query("SELECT _prodId, prodName, prodRetailPrice, prodType, prodDesc FROM product")
    public List<Product> getInventReports();

    //@Query("Update product set name = 'test', email = 'test@test.com' where _id == :id")
    //public void updateProductById(int id);
}
