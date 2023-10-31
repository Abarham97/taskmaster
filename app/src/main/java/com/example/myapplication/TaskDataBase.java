package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Task.class},version = 1)
@TypeConverters({TaskConverter.class})
public abstract class TaskDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
