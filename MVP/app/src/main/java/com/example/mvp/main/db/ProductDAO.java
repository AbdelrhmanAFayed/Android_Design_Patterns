package com.example.mvp.main.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.mvp.main.model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM product_table")
    LiveData<List<Product>> getAllProducts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);


}

