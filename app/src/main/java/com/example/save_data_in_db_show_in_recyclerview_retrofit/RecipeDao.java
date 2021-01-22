package com.example.save_data_in_db_show_in_recyclerview_retrofit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    List<Recipe> getAll();

    @Insert
    void insert(Recipe recipe);

    @Query("DELETE FROM Recipe")
    public void deleteTable();
}
