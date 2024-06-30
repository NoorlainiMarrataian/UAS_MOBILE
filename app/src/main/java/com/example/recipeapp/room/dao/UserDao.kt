package com.example.recipeapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeapp.room.entity.UsersEntity

@Dao
interface UserDao {
    @Insert
    fun insert(user: UsersEntity): Long

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    fun login(email: String, password: String): LiveData<UsersEntity>
}
