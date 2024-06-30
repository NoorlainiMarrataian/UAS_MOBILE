package com.example.recipeapp.room.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.room.dao.UserDao
import com.example.recipeapp.room.entity.UsersEntity

@Database(entities = [UsersEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
