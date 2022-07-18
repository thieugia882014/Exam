package com.example.exam.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmpoyyeeDao {
    @Insert
    long inserUser(Employee employee);

    @Update
    int updateUser(Employee employee);

    @Delete
    int deleteUser(Employee employee);

    @Query("Select * from Employee where id = :id")
    Employee findUser(int id);

    @Query("Select * from employee")
    List<Employee> getAllUser();
}
