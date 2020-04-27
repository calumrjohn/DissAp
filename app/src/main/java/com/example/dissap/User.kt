package com.example.dissap

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val username: String,//Natural Key.
    val password: String,
    val email: String,
    val studentNo: String,
    val telNo: String
)

