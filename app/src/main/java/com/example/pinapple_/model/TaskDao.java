package com.example.pinapple_.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task... tasks);

    @Query("DELETE FROM tasks")
    void deleteAll();
}
