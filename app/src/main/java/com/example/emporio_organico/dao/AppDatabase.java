package com.example.emporio_organico.dao;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.emporio_organico.entity.Product;

@Database(entities = {Product.class}, version = 1,exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    public abstract ProductDAO createProductDAO();

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"emporio organico").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}