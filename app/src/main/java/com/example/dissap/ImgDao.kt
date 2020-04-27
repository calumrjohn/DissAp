package com.example.dissap

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImgDao {
    @Insert
    fun insert(i: Img)
    @Query("SELECT parentId FROM img WHERE parentId = :hId ")
    fun searchByHuntId(hId: String): List<Long>
    @Query("SELECT * FROM img ORDER BY parentId ASC")
    fun getAll() : List<Img>
}

