package com.example.save_data_in_db_show_in_recyclerview_retrofit;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Recipe.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}

