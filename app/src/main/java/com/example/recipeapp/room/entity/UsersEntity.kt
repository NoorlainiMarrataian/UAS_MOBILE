package com.example.recipeapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UsersEntity(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val email: String,
        val password: String,
        val name: String // add other fields as needed
)
