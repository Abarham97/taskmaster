package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;



@Database(entities = {Task.class}, version = 2)
@TypeConverters({TaskDBConverter.class})
public abstract class BuyMoreStuffDatabase extends RoomDatabase {

    public abstract TaskDao task();
}
