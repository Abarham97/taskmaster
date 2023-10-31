package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  TaskDao {

    @Insert
    public void insertATask(Task task);

    @Query("select * from Task")
    public List<Task> findAll();

    @Query("select * from Task ORDER BY title ASC")
    public List<Task> findAllSortedByTitl();

    @Query("select * from Task where id = :id")
    Task findByAnId(long id);
@Insert
    void insertTask(Task task);
@Query("select * from Task where  title= :title")
    Task findByTitle(String title);
}
