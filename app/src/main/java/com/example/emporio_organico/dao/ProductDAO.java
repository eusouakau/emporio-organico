package com.example.emporio_organico.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.emporio_organico.entity.Product;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM Product")
    public List<Product> getAllProducts();

    @Query("SELECT * FROM Product WHERE nome = :name")
    public List<Product> geProductByName(String name);

    @Insert(onConflict = REPLACE)
    public void insert(Product product);

    @Update
    public void update(Product product);

    @Delete
    public void delete(Product product);

}